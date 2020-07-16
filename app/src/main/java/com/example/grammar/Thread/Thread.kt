package com.example.grammar.Thread

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_thread.*
import java.lang.Thread

class Thread : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        
        button0.setOnClickListener {
            val runnable : Runnable = object : Runnable{
                override fun run() {
                    Log.d("thread-1", "Thread is made")
                }
            }
            val thread: Thread = Thread(runnable)
            thread.start()
        }
        
        button1.setOnClickListener {
            Thread(object : Runnable{
                override fun run() {
                    Log.d("thread-1", "Thread is made")
                }
            }).start()
        }

        //lamda 방식
        button2.setOnClickListener {
            Thread(Runnable{
                Log.d("thread-1", "Thread is made")
                Thread.sleep(1000)
                //서브Thread에서 UI관련 작업을 해야 될 경우,
                runOnUiThread{ //MainThread(UiThread)에서 run시키는 명령어
                    button2.setBackgroundColor(getColor(R.color.textview_color))
                }
            }).start()
        }
    }
}
