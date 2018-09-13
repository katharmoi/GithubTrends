package com.kadirkertis.githubtrends.data.http

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Github Api using Retrofit
 */
interface GithubApi {
    /**
     * Get repositories from Github.
     * Order by start count
     */
    @GET("search/repositories?sort=stars")
    fun getReposByStarCount(
            @Query("q") query: String,
            @Query("sort") sortCriteria: String,
            @Query("order") order: String
    ): Single<RetrofitRepoResponse>
}