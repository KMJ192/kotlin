package com.example.grammar.Database

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        //SharedPreference에 저장하는 방법

        //SharedPreference
        //MODE
        // - MODE_PRIVATE : 생성한 application에서만 사용 가능
        // - MODE_WORLDE_READABLE : 다른 application에서 사용 가능 -> readonly
        // - MODE_WORLDE_WRITEABLE : 다른 application에서 사용 가능 -> read, write // 2개의 앱을 상호작용할 필요가 있을 때
        // - MODE_MULTI_PROCESS : 이미 호출되어 사용중인지 체크
        // - MODE_APPEND : 기존 preference에 신규로 추가

        //에디터를 이용하여 shardPreferenc에 Data Input
//        val editor : SharedPreferences.Editor = sharedPreference.edit()
//        editor.putString("hello", "안뇽?")
//        editor.commit()

        save_btn.setOnClickListener {
            val sharedPreference =getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("hello", "안뇽?")
            editor.putString("goodbye", "잘가")
            editor.commit() // commit ==>SharedPreferneces에 Data Input
        }

        load.setOnClickListener {
            //SharedPrefernece에 값 불러오는 방법
            val sharedPreference =getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val value1 = sharedPreference.getString("hello", "NoData") //defValue(DefaultValue) : DB에 데이터가 없을 경우 기본값
            val value2 = sharedPreference.getString("goodbye", "NoData") //defValue(DefaultValue) : DB에 데이터가 없을 경우 기본값
            Log.d("Key-Value","Value : $value1")
            Log.d("Key-Value","Value : $value2")
        }

        delete_button.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("hello")
            editor.commit()
        }
        delete_all_button.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear() 
            editor.commit()
        }
        //마지막에느 항상 commit 입력 필요

    }
}
