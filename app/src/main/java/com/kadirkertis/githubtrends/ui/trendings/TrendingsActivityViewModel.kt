package com.kadirkertis.githubtrends.ui.trendings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kadirkertis.domain.interactor.trending.GetTrendingReposUseCase
import com.kadirkertis.domain.interactor.trending.model.Repo
import com.kadirkertis.githubtrends.util.Response
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * [TrendingsActivity] ViewModel
 * Gets trending android repositories through [GetTrendingReposUseCase]
 */
class TrendingsActivityViewModel(private val getTrendingReposUseCase: GetTrendingReposUseCase,
                                 private val mainThreadSchedular: Scheduler) : ViewModel() {

    val response = MutableLiveData<Response<List<Repo>>>()
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    internal fun loadTrendingRepos() {
        disposables.add(getTrendingReposUseCase.execute()
                .doOnSubscribe { _ -> response.setValue(Response.loading()) }
                .observeOn(mainThreadSchedular)
                .subscribe(
                        { result -> response.setValue(Response.success(result)) },
                        { error -> response.setValue(Response.error(error)) }
                )
        )
    }
}