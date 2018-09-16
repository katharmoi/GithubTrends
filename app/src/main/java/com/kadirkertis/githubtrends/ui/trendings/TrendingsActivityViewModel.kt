package com.kadirkertis.githubtrends.ui.trendings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kadirkertis.domain.interactor.trending.GetNextPageUseCase
import com.kadirkertis.domain.interactor.trending.GetTrendingReposUseCase
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.util.Response
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * [TrendingsActivity] ViewModel
 * Gets trending android repositories through [GetTrendingReposUseCase]
 */
class TrendingsActivityViewModel(private val getNextPageUseCase: GetNextPageUseCase,
                                 private val mainThreadSchedular: Scheduler) : ViewModel() {

    val response = MutableLiveData<Response<List<Repo>>>()
    private val disposables = CompositeDisposable()

    //prevent simultaneous multiple reguests
    private var isUpdating = false

    override fun onCleared() {
        disposables.clear()
    }

    internal fun loadTrendingRepos() {
        disposables.add(getNextPageUseCase.execute()
                .onBackpressureDrop()
                .doOnSubscribe { response.setValue(Response.loading()); isUpdating = true }
                .doOnEach { isUpdating = false }
                .doOnError { isUpdating = false }
                .observeOn(mainThreadSchedular)
                .subscribe(
                        { response.setValue(Response.success(it)) },
                        { response.setValue(Response.error(it)) }
                )
        )
    }

    fun scrollListener(visibleItemCount: Int, lastVisibleItem: Int, totalItemCount: Int) {
        if (!isUpdating && totalItemCount <= (visibleItemCount + lastVisibleItem + LOADING_POINT)) {
            loadTrendingRepos()
        }
    }

    companion object {
        private const val LOADING_POINT: Int = 5
    }
}