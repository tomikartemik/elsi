package com.komandor.komandor.app.initializer

import android.text.TextUtils
import android.util.Log
import timber.log.Timber

class DebugTimberTree: Timber.Tree()  {
    val TAG: String ="KomandorPro"

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        // Don't log VERBOSE and DEBUG
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            //return
        }


        if (message.length > 4000) {
            val chunkCount: Int = message.length / 4000 // integer division
            for (i in 0..chunkCount) {
                val max = 4000 * (i + 1)
                if (max >= message.length) {
                    Log.println(priority, TAG, "${getLocation()}: ${message.substring(4000 * i)}")
                } else {
                    Log.println(priority, TAG, "${getLocation()}: ${message.substring(4000 * i, max)}")
                }
            }
        } else {
            Log.println(priority, TAG, "${getLocation()}: $message")
        }
    }

    private fun getLocation(): String? {
        val traces = Thread.currentThread().stackTrace
        var found = false
        for (i in traces.indices) {
            val trace = traces[i]
            //Log.d(TAG, " trace getClassName = " + trace.getClassName()+" "+trace.getMethodName());
            try {
                if (found) {
                    if (!trace.className.contains("Timber")) {
                        val clazz = Class.forName(trace.className)
                        return "[" + getClassName(clazz) + ":" + trace.methodName + ":" + trace.lineNumber + "] "
                    }
                } else if (trace.className.contains("Timber")) {
                    found = true
                    continue
                }
            } catch (e: ClassNotFoundException) {
            }
        }
        return "[]: "
    }

    private fun getClassName(clazz: Class<*>?): String {
        return if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.simpleName)) {
                clazz.simpleName
            } else getClassName(clazz.enclosingClass)
        } else ""
    }
}