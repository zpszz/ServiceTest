package com.jpc.servicetest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import androidx.core.app.NotificationCompat

/**
 * @ClassName FrontService
 * @Description TODO
 * @Author zzps
 * @Date 2024/6/15 17:30
 * @Version 1.0
 */
class FrontService : Service(){
    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            "my_service",
            "前台Service通知",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        manager.createNotificationChannel(channel)
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("Content title")
            .setContentText("content text")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
            .setContentIntent(pendingIntent)
            .build()
        // 第一个参数是通知的id，类似于notify()方法的第一个参数；第二个参数则是
        //构建的Notification对象。调用startForeground()方法后就会让MyService变成一个前
        //台Service，并在系统状态栏显示出来。
        startForeground(1, notification)
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}