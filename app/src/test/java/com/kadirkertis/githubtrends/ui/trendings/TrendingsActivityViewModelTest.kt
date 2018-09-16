package com.kadirkertis.githubtrends.ui.trendings

import com.kadirkertis.domain.interactor.trending.GetNextPageUseCase
import com.kadirkertis.domain.interactor.trending.TestData
import com.kadirkertis.domain.interactor.model.Repo
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class TrendingsActivityViewModelTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!


    @Mock
    lateinit var getNextPageUseCase: GetNextPageUseCase

    internal lateinit var underTest: TrendingsActivityViewModel

    val testSubscriber: TestSubscriber<List<Repo>> = TestSubscriber()

    val testScheduler: TestScheduler = TestScheduler()

    @Before
    fun setUp() {
        underTest = TrendingsActivityViewModel(getNextPageUseCase, testScheduler)
    }

    @Test
    fun `should load new page on scroll down`() {

        val client = simulateRepository(1)
        Mockito.`when`(getNextPageUseCase.execute())
                .thenReturn(simulateRepository(1))

        client.subscribe(testSubscriber)

        sendScrollEvents()


        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)


        Mockito.verify(getNextPageUseCase, Mockito.times(1)).execute()

        testSubscriber.assertNoValues()

    }

    @Test
    fun `should not update when update is in progress`() {

        val client = simulateRepository(1)
        Mockito.`when`(getNextPageUseCase.execute())
                .thenReturn(simulateRepository(1))

        client.subscribe(testSubscriber)

        sendScrollEvents()

        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)


        Mockito.verify(getNextPageUseCase, Mockito.times(1)).execute()

        testSubscriber.assertNoValues()

    }

    //Get Data from repo in 1 second
    fun simulateRepository(page: Int): Flowable<List<Repo>> {
        return Flowable.just(true)
                .delay(600, TimeUnit.MILLISECONDS)
                .map { value ->
                    val items = arrayListOf<Repo>()
                    items.add(TestData.TEST_REPO)
                    items
                }
    }

    //Send Scroll events Every 100 ms
    fun sendScrollEvents() {
        Observable.interval(0, 100, TimeUnit.MILLISECONDS)
                .map { underTest.scrollListener(1, 1, 1) }
                .subscribe()
    }
}