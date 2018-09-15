package com.kadirkertis.githubtrends.di.application.shared

import com.kadirkertis.domain.interactor.trending.GetNextPageUseCase
import com.kadirkertis.domain.interactor.trending.GetTrendingReposUseCase
import com.kadirkertis.domain.interactor.trending.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provide Interactors
 */
@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun provideGetTrendingReposUsecase(githubRepository: GithubRepository): GetTrendingReposUseCase {
        return GetTrendingReposUseCase(githubRepository)
    }

    @Provides
    @Singleton
    fun provideGetNextPageUsecase(getTrendingReposUseCase: GetTrendingReposUseCase):GetNextPageUseCase {
        return GetNextPageUseCase(getTrendingReposUseCase)
    }
}