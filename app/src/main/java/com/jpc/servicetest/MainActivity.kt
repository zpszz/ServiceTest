package com.jpc.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val updateText = 1 // 用于标识哪个动作
    private lateinit var textView: TextView
    // Handler顾名思义也就是处理者的意思，它主要是用于发送和处理消息的。发送消息一般
    //是使用Handler的sendMessage()方法、post()方法等，而发出的消息经过一系列地辗
    //转处理后，最终会传递到Handler的handleMessage()方法中
    private val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            // 在主线程中进行UI操作
            when(msg.what){
                updateText -> textView.text = msg.obj.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<TextView>(R.id.textView)
        val changeTextBtn = findViewById<Button>(R.id.changeTextBtn)
        changeTextBtn.setOnClickListener{
            // 开启一个新的线程
            thread {
                // Message是在线程之间传递的消息，它可以在内部携带少量的信息，用于在不同线程之间传递数据
                val message = Message()
                message.what = updateText
                message.arg1 = 1 // 携带整型数据
                message.arg2 = 2
                message.obj = "你好啊" // 携带对象类型数据
                // 发送Message对象
                handler.sendMessage(message)
                // 通过post方法发送一个 Runnable 对象，这个 Runnable 对象会被添加到消息队列的尾部
                handler.post(Runnable { Log.d("Handler", "post") })
            }
        }
    }
}