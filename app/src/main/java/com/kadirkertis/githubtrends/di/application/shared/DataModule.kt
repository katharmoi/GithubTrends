package com.kadirkertis.githubtrends.di.application.shared

import android.content.Context
import android.util.Log
import com.jakewharton.picasso.OkHttp3Downloader
import com.kadirkertis.domain.interactor.trending.repository.GithubRepository
import com.kadirkertis.githubtrends.BuildConfig
import com.kadirkertis.githubtrends.data.db.RepoDao
import com.kadirkertis.githubtrends.data.http.GithubApi
import com.kadirkertis.githubtrends.data.http.GithubService
import com.kadirkertis.githubtrends.data.http.GithubServiceImpl
import com.kadirkertis.githubtrends.data.mappers.DataToViewMapper
import com.kadirkertis.githubtrends.data.repository.GithubRepositoryImpl
import com.kadirkertis.githubtrends.util.NetworkUtils
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Providers for repository, service and Retrofit objects
 */
@Module
class DataModule {

    companion object {
        private val CACHE_CONTROL = "cache-control"
        private const val OKHTTP_NETWORK_CACHE = "network-cache"
        private const val OKHTTP_OFFLINE_CACHE_DURATION = "offline-cache"
    }

    @Singleton
    @Provides
    fun provideGithubRepository(githubService: GithubService,
                                dao: RepoDao,
                                mapper: DataToViewMapper): GithubRepository {
        return GithubRepositoryImpl(githubService, dao, mapper)
    }


    @Singleton
    @Provides
    fun provideGithubService(githubApi: GithubApi): GithubService {
        return GithubServiceImpl(githubApi)
    }


    @Singleton
    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache,
                            loggingInterceptor: HttpLoggingInterceptor,
                            @Named(OKHTTP_NETWORK_CACHE) cacheInterceptor: Interceptor,
                            @Named(OKHTTP_OFFLINE_CACHE_DURATION) offlineCacheInterceptor: Interceptor): OkHttpClient {

//        if (BuildConfig.DEBUG) {
        //            IdlingResources.registerOkHttp(client);
        //        }
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(offlineCacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .build()
    }

    @Singleton
    @Provides
    fun provideCache(context: Context): Cache {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http_cache"), (10 * 1024 * 1024).toLong()) //10MB
        } catch (e: Exception) {
            Log.d("RETROFIT", "Cannot create cache")
        }

        return cache!!
    }

    @Singleton
    @Provides
    @Named(OKHTTP_NETWORK_CACHE)
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                    .maxAge(30, TimeUnit.SECONDS)
                    .build()

            response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
    }

    @Singleton
    @Provides
    @Named(OKHTTP_OFFLINE_CACHE_DURATION)
    fun provideOfflineCacheInterceptor(networkUtils: NetworkUtils): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!networkUtils.isConnected()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build()

                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
            }

            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build()
    }


    @Singleton
    @Provides
    fun providePicasso(context: Context, okHttpClient: OkHttpClient): Picasso {
        return Picasso.Builder(context)
                .downloader(OkHttp3Downloader(okHttpClient))
                .build()
    }
}