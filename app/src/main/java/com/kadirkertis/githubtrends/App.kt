package com.kadirkertis.githubtrends

import com.kadirkertis.githubtrends.di.application.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }
}