package com.example.jarvis

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

/**
 * Foreground-service scaffold.
 *
 * This keeps the app's intended microphone-service lifecycle explicit.
 * It does NOT yet detect the words "Hey JARVIS". Add a wake-word engine
 * (for example Picovoice Porcupine) as described in README.md.
 */
class WakeWordService : Service() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification())
        // TODO: Initialize wake-word engine here after adding your licensed
        // engine dependency, access key, and Android .ppn keyword model.
        // Do not start an unbounded SpeechRecognizer loop as a substitute.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        // TODO: Stop and release wake-word engine/audio resources here.
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, "JARVIS listening", NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Visible status while wake-word listening is enabled." }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(this)
        }
        return builder
            .setContentTitle("JARVIS listening service")
            .setContentText("Microphone service is active. Wake-word engine setup is still required.")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setOngoing(true)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "jarvis_wake_word"
        private const val NOTIFICATION_ID = 4102
    }
}
