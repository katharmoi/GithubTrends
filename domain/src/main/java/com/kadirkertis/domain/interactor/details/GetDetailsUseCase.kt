package com.kadirkertis.domain.interactor.details

import com.kadirkertis.domain.interactor.repository.GithubRepository
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.domain.type.MaybeUseCaseWithParameter
import io.reactivex.Maybe


class GetDetailsUseCase(private val githubRepository: GithubRepository)
    : MaybeUseCaseWithParameter<Repo, Int> {

    override fun execute(param: Int): Maybe<Repo> {
        return githubRepository.getRepoDetailsById(param)
    }
}