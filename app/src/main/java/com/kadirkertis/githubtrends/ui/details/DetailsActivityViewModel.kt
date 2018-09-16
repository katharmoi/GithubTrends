package com.kadirkertis.githubtrends.ui.details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kadirkertis.domain.interactor.details.GetDetailsUseCase
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.util.Response
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class DetailsActivityViewModel(private val getDetailsUseCase: GetDetailsUseCase,
                               private val mainThreadSchedular: Scheduler) : ViewModel() {

    val response = MutableLiveData<Response<Repo>>()
    private val disposables = CompositeDisposable()


    override fun onCleared() {
        disposables.clear()
    }

    internal fun loadDetails(id: Int) {
        disposables.add(getDetailsUseCase.execute(id)
                .doOnSubscribe { response.setValue(Response.loading()) }
                .observeOn(mainThreadSchedular)
                .subscribe(
                        { response.setValue(Response.success(it)) },
                        { response.setValue(Response.error(it)) }
                )
        )
    }
}