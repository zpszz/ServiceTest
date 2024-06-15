package com.jpc.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class MyService : Service() {
    private val mBinder = DownloadBinder()
    // 当其他组件通过bindService来绑定服务时被调用
    override fun onBind(intent: Intent): IBinder {
        // 返回一个IBinder对象
        return mBinder
    }

    // 在Service被创建时调用
    override fun onCreate() {
        super.onCreate()
    }

    // 当其他组件通过startService来请求启动Service时，该方法被调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            // 开启一个线程执行耗时操作
            stopSelf() // 操作执行完毕关闭Service
        }
        return super.onStartCommand(intent, flags, startId)
    }
    // 当服务不再有用或者被销毁时，系统调用该方法
    override fun onDestroy() {
        super.onDestroy()
    }
    // 当客户中断所有服务发布的特殊接口时，系统调用该方法
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }
        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }
}