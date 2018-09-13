package com.kadirkertis.githubtrends.di.application

import com.kadirkertis.githubtrends.di.activity.ActivityScope
import com.kadirkertis.githubtrends.ui.details.DetailsActivity
import com.kadirkertis.githubtrends.ui.trendings.TrendingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun bindTrendingsActivity(): TrendingsActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun bindDetailsActivity(): DetailsActivity

}