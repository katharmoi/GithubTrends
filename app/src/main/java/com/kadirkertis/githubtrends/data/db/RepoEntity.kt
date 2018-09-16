package com.kadirkertis.githubtrends.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Repo Entity Object For Room
 */
@Entity(tableName = "repositories")
data class RepoEntity(
        @PrimaryKey val id: Long,
        val name: String,
        val fullName: String,
        val url: String,
        val language: String?,
        val description: String?,
        val stars: Int,
        val forks: Int,
        val avatarUrl: String?
)