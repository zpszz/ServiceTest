package com.jpc.servicetest

import android.app.IntentService
import android.content.Intent

/**
 * @ClassName MyIntentService
 * @Description TODO
 * @Author zzps
 * @Date 2024/6/15 19:38
 * @Version 1.0
 */
class MyIntentService: IntentService("MyIntentService"){
    // 新增了一个方法，该方法已经在子线程中执行，所以将耗时操作放在这里
    override fun onHandleIntent(intent: Intent?) {
        TODO("Not yet implemented")
    }
    // 其他方法和普通Service一样
    override fun onCreate() {
        super.onCreate()
    }
}