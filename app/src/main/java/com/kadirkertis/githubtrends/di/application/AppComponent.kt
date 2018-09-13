package com.kadirkertis.githubtrends.di.application

import com.kadirkertis.githubtrends.App
import com.kadirkertis.githubtrends.di.application.shared.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Object Graph for the app level singletons
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    BuildersModule::class,
    AppModule::class,
    DataModule::class,
    ThreadingModule::class,
    RoomModule::class,
    UseCasesModule::class,
    UtilsModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
