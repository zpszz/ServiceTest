package com.jpc.servicetest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * @ClassName MainActivity2
 * @Description TODO
 * @Author zzps
 * @Date 2024/6/15 17:13
 * @Version 1.0
 */
class MainActivity2: AppCompatActivity(){
    private lateinit var mBinder: MyService.DownloadBinder
    // ServiceConnection用于监听服务的状态, 以便于在服务绑定成功时执行相应的逻辑
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as MyService.DownloadBinder
            mBinder.startDownload()
            mBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 启动服务
        findViewById<Button>(R.id.startServiceBtn).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }
        // 停止服务
        findViewById<Button>(R.id.stopServiceBtn).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
        // 绑定服务,这样Activity和Service就建立了关联
        findViewById<Button>(R.id.bindServiceBtn).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            // 传入参数：intent, ServiceConnection, flags，
            // flags一般传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
        // 解绑服务
        findViewById<Button>(R.id.unbindServiceBtn).setOnClickListener {
            unbindService(connection)
        }
    }
}