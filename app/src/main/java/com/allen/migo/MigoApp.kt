package com.allen.migo

import androidx.multidex.MultiDexApplication
import com.allen.migo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MigoApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MigoApp)
            modules(appModule)
        }
    }
}