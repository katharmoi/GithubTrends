package com.kadirkertis.githubtrends.data.mappers

import com.kadirkertis.domain.interactor.model.Owner
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.data.model.RepoData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Maps data [RepoData] model objects to view [Repo] objects
 */

@Singleton
class DataToViewMapper @Inject constructor() : (RepoData) -> Repo {
    override fun invoke(repoData: RepoData): Repo = Repo(
            repoData.id,
            repoData.name,
            repoData.fullName,
            repoData.url,
            repoData.language,
            repoData.description,
            repoData.stars,
            repoData.forks,
            Owner(repoData.owner.id.toString(), repoData.owner.avatarUrl))
}
