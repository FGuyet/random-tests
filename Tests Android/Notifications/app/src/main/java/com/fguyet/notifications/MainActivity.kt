package com.fguyet.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mNotifyManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notify_button.setOnClickListener { sendNotification() }

        createNotificationChannel()
    }

    private fun sendNotification() {
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
    }

    private fun getNotificationBuilder() = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
        .setContentTitle("You've been notified!")
        .setContentText("This is the notification text.")
        .setSmallIcon(R.drawable.ic_android)

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Primary notification channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "This is the description."
            }
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
    }
}