package com.kadirkertis.githubtrends.data.repository

import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.domain.interactor.repository.GithubRepository
import com.kadirkertis.githubtrends.data.db.RepoDao
import com.kadirkertis.githubtrends.data.http.GithubService
import com.kadirkertis.githubtrends.data.mappers.DataToEntityMapper
import com.kadirkertis.githubtrends.data.mappers.EntityToViewMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Repository implementaion: fetches data from local db and remote
 */
class GithubRepositoryImpl(
        private val service: GithubService,
        private val cache: RepoDao,
        private val entityToVewMapper: EntityToViewMapper,
        private val dataToEntityMapper: DataToEntityMapper
) : GithubRepository {

    /**
     * Get repository details by repository id
     */
    override fun getRepoDetailsById(id: Int): Maybe<Repo> {
        return cache.getRepoById(id)
                .subscribeOn(Schedulers.io())
                .map { entityToVewMapper.invoke(it) }
    }

    /**
     * Emits data from the db first then updates the db from remote
     * and emits new data if there is any update.
     */
    override fun getTrendingRepositoriesByPage(page: Int): Flowable<List<Repo>> {
        return cache.getReposByStarCount()
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(Flowable.just(emptyList()))
                .doOnSubscribe {
                    service.getTrendingAndroidReposByPage(page, 30)
                            .subscribeOn(Schedulers.io())
                            .onErrorResumeNext(Single.just(emptyList()))
                            .map { list -> list.map { dataToEntityMapper.invoke(it) } }
                            .doAfterSuccess {
                                Completable.fromCallable { cache.insert(it) }
                                        .subscribeOn(Schedulers.io()).subscribe()
                            }
                            .subscribe()

                }
                .map { list -> list.map { entityToVewMapper.invoke(it) } }
    }

}