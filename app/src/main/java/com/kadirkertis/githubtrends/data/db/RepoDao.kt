package com.kadirkertis.githubtrends.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Data access object for repositories table
 */
@Dao
interface RepoDao {

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RepoEntity>)

    /**
     * Get trendings repositorie from database using ordering them
     * by stars
     */
    @Query("SELECT * FROM repositories ORDER BY stars DESC, name ASC")
    fun getReposByStarCount(): Flowable<List<RepoEntity>>

    @Query("SELECT * FROM repositories WHERE id = :repoId")
    fun getRepoById(repoId: Int): Maybe<RepoEntity>

}