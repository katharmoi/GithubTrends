package com.kadirkertis.githubtrends.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Db for repo
 */

@Database(
        entities = [RepoEntity::class],
        version = 1,
        exportSchema = false
)
abstract class RepoDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}