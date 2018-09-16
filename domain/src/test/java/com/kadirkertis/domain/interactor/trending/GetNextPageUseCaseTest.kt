package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.domain.interactor.repository.GithubRepository
import io.reactivex.Flowable
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
class GetNextPageUseCaseTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var githubRepository: GithubRepository

    val testSubscriber: TestSubscriber<List<Repo>> = TestSubscriber()

    internal lateinit var getTrendingReposUseCase: GetTrendingReposUseCase

    internal lateinit var underTest: GetNextPageUseCase

    @Before
    fun setUp() {
        getTrendingReposUseCase = GetTrendingReposUseCase(githubRepository)
        underTest = GetNextPageUseCase(getTrendingReposUseCase)
    }


    @Test
    fun `when next page requested should call repository and return test data `() {
        val repos = arrayListOf<Repo>()
        repos.add(TestData.TEST_REPO)


        Mockito.`when`(githubRepository.getTrendingRepositoriesByPage(1))
                .thenReturn(Flowable.just(repos))

        underTest.execute().subscribe(testSubscriber)

        Mockito.verify(githubRepository, Mockito.times(1)).getTrendingRepositoriesByPage(1)
        Mockito.verifyNoMoreInteractions(githubRepository)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(repos)
    }

    @Test
    fun `when next page requested should increment last page `() {
        val repos = arrayListOf<Repo>()
        repos.add(TestData.TEST_REPO)


        Mockito.`when`(githubRepository.getTrendingRepositoriesByPage(1))
                .thenReturn(simulateRepository(1))

        underTest.execute().subscribe(testSubscriber)

        Mockito.verify(githubRepository, Mockito.times(1)).getTrendingRepositoriesByPage(1)
        Mockito.verifyNoMoreInteractions(githubRepository)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(repos)
    }


    fun simulateRepository(page: Int): Flowable<List<Repo>> {
        return Flowable.just(true)
                .delay(1, TimeUnit.SECONDS)
                .map { value ->
                    val items = arrayListOf<Repo>()
                    items.add(TestData.TEST_REPO)
                    items
                }
    }
}