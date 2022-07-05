package com.komandor.komandor.ui.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.komandor.komandor.R
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.viewmodel.PreloadingViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PreloadingFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_loading
    private val viewModel by viewModels<PreloadingViewModel>()

    override fun initUI() {
        initStateWatcher(viewModel.state)
        //initFcm()
        viewModel.hasUserContainers(requireContext())
    }

    override fun afterError(exception: Throwable) {
        toScreen(R.id.startInfoFragment)
    }

    override fun onComplete(data: Any?) {
        if (data != null && data is Boolean) {
            toScreen(if (data) R.id.certificateValidationFragment else R.id.startInfoFragment)
        }
    }

    override fun back() {
        requireActivity().finish()
    }

    fun initFcm() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager =
                requireContext().getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW
                )
            )
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        requireActivity().intent.extras?.let {
            for (key in it.keySet()) {
                val value = it.getString(key)
                Timber.d("Key: $key Value: $value")
            }
        }
        // [END handle_data_extras]


        // [END subscribe_topics]
        Timber.d("Fcm getToken")

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.d("Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                Timber.d("Fcm getToken token = ${token}")
                viewModel.registerFcm(requireContext(), token)

            }).addOnFailureListener {
                Timber.d("fcm getToken err = ${it}")
            }
        // [END log_reg_token]
        /*
        Timber.d( "Subscribing to weather topic")
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Timber.d( msg)
            }

         */
    }
}