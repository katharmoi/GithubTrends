package com.kadirkertis.githubtrends.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kadirkertis.githubtrends.data.model.RepoData

/**
 * Db for repo
 */

@Database(
        entities = [RepoData::class],
        version = 1,
        exportSchema = false
)
abstract class RepoDb: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}