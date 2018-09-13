package com.kadirkertis.githubtrends.data.repository

import com.kadirkertis.domain.interactor.trending.model.Repo
import com.kadirkertis.domain.interactor.trending.repository.GithubRepository
import com.kadirkertis.githubtrends.data.db.RepoDao
import com.kadirkertis.githubtrends.data.http.GithubService
import com.kadirkertis.githubtrends.data.mappers.DataToViewMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * Repository implementaion: fetches data from local db and remote
 */
class GithubRepositoryImpl(
        private val service: GithubService,
        private val cache: RepoDao,
        private val mapper: DataToViewMapper
) : GithubRepository {

    /**
     * Emits data from the db first then updates the db from remote
     * and emits new data if updated.
     */
    override fun getTrendingRepositories(): Flowable<List<Repo>> {
        return cache.getReposByStarCount()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    service.getTrendingAndroidRepos().subscribeOn(Schedulers.io())
                            .subscribe({ result ->
                                Completable.fromCallable { cache.insert(result) }.subscribeOn(Schedulers.io()).subscribe()
                            },
                                    { error -> })
                }
                .map { list -> list.map { mapper.invoke(it) } }

    }


}