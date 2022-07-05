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

/*


    override val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            var isGranted = permissions.entries.fold(true) { r, p -> r && p.value }
            if (isGranted) {
                Timber.d("certificateInit")
                viewModel.certificateInit(requireContext())
            } else {
                //L.d(TAG, "Permission: Denied")
                val r = shouldShowRequestPermission(requireActivity(), PERMISSIONS)
                Timber.d("Permission: Denied  r = $r")
                if (r.isNotEmpty()) {
                    Timber.d(
                        " доступ к камере запрещен, пользователь поставил галочку Don't ask again."
                    )
                    //onRequestPermission(requireActivity(), PERMISSIONS);

                } else {
                    Timber.d("  доступ к камере запрещен")
                    //selectImage()
                }
            }
        }


 */
interface  IPermissionMulti {
    val requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    /*
    val PERMISSIONS = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

     */

    fun checkPermissions(context: Context, PERMISSIONS: Array<String>): Boolean {
        return PERMISSIONS.fold(false) { r, p ->
            r && ContextCompat.checkSelfPermission(
                context,
                p
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun shouldShowRequestPermission(activity: Activity, PERMISSIONS: Array<String>): List<String> {
        return PERMISSIONS.filter { p ->
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                p
            )
        }
    }

    fun onRequestPermission(activity: Activity, PERMISSIONS: Array<String>) {
        if (checkPermissions(activity, PERMISSIONS)) {
            // permission granted
        } else {
            val r = shouldShowRequestPermission(activity, PERMISSIONS )
            if (r.isNotEmpty()) {
                AlertDialog.Builder(activity)
                    .setTitle("Permission required")
                    .setMessage("Location is required for this application to work ! ")
                    .setPositiveButton("Allow", DialogInterface.OnClickListener { dialog, which ->
                        Timber.d("Permission: r = $r")
                        requestPermissionLauncher.launch(r.toTypedArray())
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                    .show()
            } else {
                Timber.d("Permission: else showSnackbar")
                requestPermissionLauncher.launch(PERMISSIONS)
            }
        }
    }
}