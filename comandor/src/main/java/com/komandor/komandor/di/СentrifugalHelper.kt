package com.komandor.komandor.di

import android.annotation.SuppressLint
import io.github.centrifugal.centrifuge.*
import timber.log.Timber
import java.nio.charset.StandardCharsets

class СentrifugalHelper {


    private fun subsr(token:String){
        val listener: EventListener = object : EventListener() {
            override fun onConnect(client: Client?, event: ConnectEvent) {
                Timber.d("Connected with client ID " + event.getClient())
            }

            override fun onDisconnect(client: Client?, event: DisconnectEvent) {
                Timber.d("Disconnected: " + event.reason)
            }
        }

        val client = Client(
            "wss://ws.komandor.app/connection/websocket",
            Options(),
            listener
        )
        client.setToken(token)
        client.connect()

        val subListener: SubscriptionEventListener = object : SubscriptionEventListener() {
            @SuppressLint("SetTextI18n")
            override fun onSubscribeSuccess(sub: Subscription, event: SubscribeSuccessEvent?) {
                Timber.d("Subscribed to " + sub.getChannel())
            }

            @SuppressLint("SetTextI18n")
            override fun onSubscribeError(sub: Subscription, event: SubscribeErrorEvent) {

                Timber.d(
                    "Subscribe error " + sub.getChannel()
                        .toString() + ": " + event.message
                )

            }

            @SuppressLint("SetTextI18n")
            override fun onPublish(sub: Subscription, event: PublishEvent) {
                val data = String(event.data, StandardCharsets.UTF_8)
                Timber.d(
                    "Message from " + sub.getChannel().toString() + ": " + data
                )

            }

            @SuppressLint("SetTextI18n")
            override fun onUnsubscribe(sub: Subscription, event: UnsubscribeEvent?) {
                Timber.d("Unsubscribed from " + sub.getChannel())
            }
        }

        val sub: Subscription
        sub = try {
            client.newSubscription("chat:index", subListener)
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
        sub.subscribe()
    }

}