package com.kadirkertis.githubtrends.ui.trendings

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kadirkertis.domain.interactor.trending.GetNextPageUseCase
import com.kadirkertis.githubtrends.di.activity.ActivityScope
import com.kadirkertis.githubtrends.di.application.shared.ThreadingModule
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@ActivityScope
class TrendingsActivityViewModelFactory
@Inject constructor(private val getNextPageUseCase: GetNextPageUseCase,
                    @param:Named(ThreadingModule.MAIN_SCHEDULER) private val mainThreadScheduler: Scheduler)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendingsActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrendingsActivityViewModel(getNextPageUseCase, mainThreadScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}