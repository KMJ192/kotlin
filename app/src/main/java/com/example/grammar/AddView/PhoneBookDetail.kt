package com.example.grammar.AddView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_phone_book_detail.*

class PhoneBookDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_detail)
        getPersonInfoAndDraw()

        back.setOnClickListener{
            //뒤돌아가기 함수
            onBackPressed()
        }
    }
    fun getPersonInfoAndDraw(){
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")

        person_detail_name.setText(name)
        person_detail_number.setText(number)

    }
}
