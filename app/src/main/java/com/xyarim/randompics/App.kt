package com.xyarim.randompics

import android.app.Application
import com.xyarim.randompics.di.networkModules
import com.xyarim.randompics.di.repositoryModules
import com.xyarim.randompics.di.useCaseModules
import com.xyarim.randompics.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModels +
                        repositoryModules +
                        useCaseModules +
                        networkModules
            )
        }
        instance = this
    }


    companion object {
        lateinit var instance: App
            private set
    }

}