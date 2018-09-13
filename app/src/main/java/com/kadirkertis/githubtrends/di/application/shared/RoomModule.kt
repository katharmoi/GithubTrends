package com.kadirkertis.githubtrends.di.application.shared

import android.arch.persistence.room.Room
import android.content.Context
import com.kadirkertis.githubtrends.data.db.RepoDao
import com.kadirkertis.githubtrends.data.db.RepoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides Data Access Object
 */
@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabse(context: Context): RepoDb {
        return Room.databaseBuilder(context.applicationContext, RepoDb::class.java, "App.db").build()
    }

    @Provides
    @Singleton
    fun provideRepoDao(db: RepoDb): RepoDao {
        return db.repoDao()
    }
}