package com.kadirkertis.githubtrends.data.mappers

import com.kadirkertis.githubtrends.data.db.RepoEntity
import com.kadirkertis.githubtrends.data.model.RepoData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Maps data [RepoData] model objects to view [RepoEntity] objects
 */
@Singleton
class DataToEntityMapper @Inject constructor() : (RepoData) -> RepoEntity {
    override fun invoke(repoData: RepoData): RepoEntity = RepoEntity(
            repoData.id,
            repoData.name,
            repoData.fullName,
            repoData.url,
            repoData.language,
            repoData.description,
            repoData.stars,
            repoData.forks,
            repoData.owner.avatarUrl)
}