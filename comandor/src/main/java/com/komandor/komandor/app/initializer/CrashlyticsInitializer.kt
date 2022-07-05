package com.komandor.komandor.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.komandor.komandor.BuildConfig

class CrashlyticsInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        //FirebaseApp.initializeApp(context)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
