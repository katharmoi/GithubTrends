package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.repository.GithubRepository
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.domain.type.FlowableUseCaseWithParameter
import io.reactivex.Flowable


class GetTrendingReposUseCase(private val githubRepository: GithubRepository)
    : FlowableUseCaseWithParameter<List<Repo>, Int> {

    override fun execute(param: Int): Flowable<List<Repo>> {
        return githubRepository.getTrendingRepositoriesByPage(param)
    }
}
