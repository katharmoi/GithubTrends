package com.kadirkertis.githubtrends.data.mappers

import com.kadirkertis.domain.interactor.model.Owner
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.data.db.RepoEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Maps data [RepoEntity] model objects to view [Repo] objects
 */
@Singleton
class EntityToViewMapper @Inject constructor() : (RepoEntity) -> Repo {
    override fun invoke(repoEntity: RepoEntity): Repo = Repo(
            repoEntity.id,
            repoEntity.name,
            repoEntity.fullName,
            repoEntity.url,
            repoEntity.language,
            repoEntity.description,
            repoEntity.stars,
            repoEntity.forks,
            Owner(null, null, repoEntity.avatarUrl))
}