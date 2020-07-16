package com.example.grammar.Intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_intent1.*

class Intent1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent1)

        change_acvivity.setOnClickListener {
            //putExtra => Key, Value방식 -> Key와 Value쌍으로 만들어 저장, Dictionary
            val intent1 = Intent(this@Intent1, Intent2::class.java)
            intent1.apply {
                this.putExtra("number1", 1)
                this.putExtra("number2", 2)
            }
            startActivityForResult(intent1, 0) // result의 key를 requestCode로 전달받음
        }
        //암시적 인텐트
        internet.setOnClickListener{
            val intent0 = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
            startActivity(intent0)
        }
    }
    //Intent의 result값을 받는 Function
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 0) {
            Log.d("number", requestCode.toString())
            Log.d("number", resultCode.toString())
            val result = data?.getIntExtra("result", 0)
            Log.d("result", result.toString())
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}