package com.kadirkertis.domain.interactor.trending.repository


import com.kadirkertis.domain.interactor.trending.model.Repo
import io.reactivex.Flowable

/**
 * Repository fetches data from local db and remote
 */
interface GithubRepository {

    fun getTrendingRepositoriesByPage(page:Int): Flowable<List<Repo>>

}
