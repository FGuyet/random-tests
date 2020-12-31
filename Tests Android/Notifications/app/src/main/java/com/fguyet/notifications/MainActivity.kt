package com.fguyet.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private val notificationReceiver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notify_button.setOnClickListener { sendNotification() }
        update_button.setOnClickListener { updateNotification() }
        cancel_button.setOnClickListener { cancelNotification() }
        setNotificationButtonStates(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)

        createNotificationChannel()

        registerReceiver(notificationReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION))
    }

    override fun onDestroy() {
        unregisterReceiver(notificationReceiver)
        super.onDestroy()
    }

    private fun sendNotification() {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder = getNotificationBuilder()
            .addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent)

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

        setNotificationButtonStates(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)
    }

    private fun updateNotification() {
        val image = BitmapFactory.decodeResource(resources, R.drawable.image_1)

        val notificationBuilder = getNotificationBuilder().setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(image)
                .setBigContentTitle("Notification updated!")
        )

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

        setNotificationButtonStates(isNotifyEnabled = false, isUpdateEnabled = false, isCancelEnabled = true);
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, MainActivity::class.java)

        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Here is a notification!")
            .setContentText("This is the notification text.")
            .setSmallIcon(R.drawable.ic_android)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
    }

    private fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)

        setNotificationButtonStates(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false);
    }

    private fun createNotificationChannel() {
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Primary notification channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "This is the channel description."
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun setNotificationButtonStates(isNotifyEnabled: Boolean, isUpdateEnabled: Boolean, isCancelEnabled: Boolean) {
        notify_button.isEnabled = isNotifyEnabled
        update_button.isEnabled = isUpdateEnabled
        cancel_button.isEnabled = isCancelEnabled
    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
        private const val ACTION_UPDATE_NOTIFICATION = "com.fguyet.notifications.ACTION_UPDATE_NOTIFICATION"
    }

    inner class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            updateNotification()
        }
    }
}

