package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.trending.model.Repo
import com.kadirkertis.domain.type.FlowableUseCase
import io.reactivex.Flowable

/**
 * Gets the next page from repository
 */
class GetNextPageUseCase(private val getTrendingReposUseCase:
                         GetTrendingReposUseCase) : FlowableUseCase<List<Repo>> {

    //save last requested page. When new request occures increment by one
    private var lastPage = 1

    override fun execute(): Flowable<List<Repo>> {

        return getTrendingReposUseCase.execute(lastPage++)
    }
}