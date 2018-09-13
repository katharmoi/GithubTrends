package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.trending.model.Repo
import com.kadirkertis.domain.interactor.trending.repository.GithubRepository
import com.kadirkertis.domain.type.FlowableUseCase
import io.reactivex.Flowable


class GetTrendingReposUseCase(private val githubRepository: GithubRepository) : FlowableUseCase<List<Repo>> {

    override fun execute(): Flowable<List<Repo>> {
        return githubRepository.getTrendingRepositories()
    }
}
