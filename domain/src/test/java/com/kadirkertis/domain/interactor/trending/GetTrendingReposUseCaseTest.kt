package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.domain.interactor.repository.GithubRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit


class GetTrendingReposUseCaseTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var githubRepository: GithubRepository

    val testSubscriber: TestSubscriber<List<Repo>> = TestSubscriber()

    internal lateinit var underTest: GetTrendingReposUseCase


    @Before
    fun setUp() {
        underTest = GetTrendingReposUseCase(githubRepository)
    }


    @Test
    fun `when trendig repos requested should call repository and return test data `() {
        val repos = arrayListOf<Repo>()
        repos.add(TestData.TEST_REPO)


        Mockito.`when`(githubRepository.getTrendingRepositoriesByPage(1))
                .thenReturn(Flowable.just(repos))

        underTest.execute(1).subscribe(testSubscriber)

        Mockito.verify(githubRepository, Mockito.times(1)).getTrendingRepositoriesByPage(1)
        Mockito.verifyNoMoreInteractions(githubRepository)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(repos)
    }


}