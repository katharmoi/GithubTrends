package com.kadirkertis.githubtrends.di.application.shared

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Schedulers for bg an main thread
 */

@Module
class ThreadingModule {

    companion object {
        const val MAIN_SCHEDULER = "mainSched"
        const val BG_SCHEDULAR = "bgSched"
    }

    @Singleton
    @Provides
    @Named(MAIN_SCHEDULER)
    fun provideMainSchedular(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Singleton
    @Provides
    @Named(BG_SCHEDULAR)
    fun provideBgSchedular(): Scheduler {
        return Schedulers.io()
    }
}
