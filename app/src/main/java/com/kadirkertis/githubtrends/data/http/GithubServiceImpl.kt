package com.kadirkertis.githubtrends.data.http

import com.kadirkertis.githubtrends.data.model.RepoData
import io.reactivex.Single

class GithubServiceImpl(private val githubApi: GithubApi) : GithubService {

    override fun getTrendingAndroidReposByPage(page: Int, per_page: Int): Single<List<RepoData>> {
        return githubApi.getReposByStarCount(ANDROID_QUERY, page, per_page, SORT_CRITERIA, ORDER)
                .map { response -> response.items }
    }

    companion object {
        private val ANDROID_QUERY = "android"
        private val SORT_CRITERIA = "stars"
        private val ORDER = "desc"
    }
}