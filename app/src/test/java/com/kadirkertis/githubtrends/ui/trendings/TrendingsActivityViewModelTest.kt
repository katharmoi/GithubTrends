package com.kadirkertis.githubtrends.ui.trendings

import com.kadirkertis.domain.interactor.trending.GetNextPageUseCase
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test


class TrendingsActivityViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getNextPageUseCase: GetNextPageUseCase

    internal lateinit var underTest: TrendingsActivityViewModel

    @Before
    fun setUp() {
        underTest = TrendingsActivityViewModel(getNextPageUseCase, Schedulers.trampoline())
    }

    @Test
    fun `should return first page when opened`() {

    }
}