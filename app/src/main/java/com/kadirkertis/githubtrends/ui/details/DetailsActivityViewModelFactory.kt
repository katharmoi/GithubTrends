package com.kadirkertis.githubtrends.ui.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kadirkertis.domain.interactor.details.GetDetailsUseCase
import com.kadirkertis.githubtrends.di.activity.ActivityScope
import com.kadirkertis.githubtrends.di.application.shared.ThreadingModule
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named


@ActivityScope
class DetailsActivityViewModelFactory
@Inject constructor(private val getDetailsUseCase: GetDetailsUseCase,
                    @param:Named(ThreadingModule.MAIN_SCHEDULER) private val mainThreadScheduler: Scheduler)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsActivityViewModel(getDetailsUseCase, mainThreadScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
