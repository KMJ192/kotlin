package com.example.grammar.NullSafety

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.grammar.R

class NullSafety : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)


        val number : Int = 10
        val number1: Int? = null

        val number3 = number1?.plus(number)

        //삼항연산자 => elvis연산자
        val number4 = number1 ?: 10 // number1이 null이 아니면 number1 입력, 아니면 10 입력
    }

    fun plus(a:Int, b:Int?) : Int?{
        return b?.plus(a)
    }
}
