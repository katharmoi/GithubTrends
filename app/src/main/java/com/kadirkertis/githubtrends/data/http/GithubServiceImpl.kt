package com.kadirkertis.githubtrends.data.http

import com.kadirkertis.githubtrends.data.model.RepoData
import io.reactivex.Single

class GithubServiceImpl(private val githubApi: GithubApi) : GithubService {

    override fun getTrendingAndroidRepos(): Single<List<RepoData>> {
        return githubApi.getReposByStarCount(ANDROID_QUERY,SORT_CRITERIA, ORDER)
                .map { response -> response.items  }
    }

    companion object {
        private val ANDROID_QUERY = "android"
        private val SORT_CRITERIA = "stars"
        private val ORDER = "desc"
    }
}