package com.komandor.komandor.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.komandor.komandor.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) return
        Timber.plant(DebugTimberTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}

