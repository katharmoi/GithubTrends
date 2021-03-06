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
     */
    @GET("search/repositories")
    fun getReposByStarCount(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int,
            @Query("sort") sortCriteria: String,
            @Query("order") order: String
    ): Single<RetrofitRepoResponse>
}