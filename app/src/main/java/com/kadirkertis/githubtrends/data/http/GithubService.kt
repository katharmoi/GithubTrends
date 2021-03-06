package com.kadirkertis.githubtrends.data.http

import com.kadirkertis.githubtrends.data.model.RepoData
import io.reactivex.Single

/**
 * API Service interface
 */
interface GithubService {
    fun getTrendingAndroidReposByPage(page: Int, per_page: Int): Single<List<RepoData>>

}