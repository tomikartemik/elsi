package com.komandor.komandor.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import timber.log.Timber

interface  IPermission {
    val requestPermissionLauncher: ActivityResultLauncher<String>

    fun checkPermissions(context: Context, PERMISSION: String): Boolean {
        return ContextCompat.checkSelfPermission(
                context,
                PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
    }

    fun shouldShowRequestPermission(activity: Activity, PERMISSION: String):Boolean {
            return ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                PERMISSION
            )

    }

    fun onRequestPermission(activity: Activity, PERMISSION: String) {
        val r = shouldShowRequestPermission(activity, PERMISSION )
        if (r) {
            AlertDialog.Builder(activity)
                .setTitle("Permission required")
                .setMessage("Location is required for this application to work ! ")
                .setPositiveButton("Allow", DialogInterface.OnClickListener { dialog, which ->
                    Timber.d("Permission: r = $r")
                    requestPermissionLauncher.launch(PERMISSION)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        } else {
            Timber.d("Permission: shouldShowRequestPermission")
            requestPermissionLauncher.launch(PERMISSION)
        }


    }
}