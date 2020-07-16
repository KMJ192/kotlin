package com.example.grammar.Database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.grammar.R
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*

class RealmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        //Realm의 저장, 삭제, 불러오기
        Realm.init(this@RealmActivity)
        val config : RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded() //Migration(DB와 동기화)이 필요해진 경우 Realm을 지우는 함수(DB의 틀에 변경이 생길 경우(컬럼추가, 변경, 삭제 등))
            .build()
        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance() //realm Get

        //table 생성
        //저장 버튼
        button_save.setOnClickListener {
            //트랜젝션 실행
            realm.executeTransaction{
                with(it.createObject(School::class.java)){
                    this.name = "대학교"
                    this.location = "뉴욕"
                }
            }
        }
        button_load.setOnClickListener {
            realm.executeTransaction{
                //it.where -> 테이블로 이동
                //findAll -> 테이블의 모든 데이터를 가져옴
                //findFirst -> 테이블의 첫번째 데이터를 가져옴
                val data = it.where(School::class.java).findFirst()
                Log.d("data", "data" + data)
            }
        }
        button_delete.setOnClickListener {
            realm.executeTransaction{
                //it.where(테이블)의 모든 내용을 지움
                it.where(School::class.java).findAll().deleteAllFromRealm()
                //it.where(School::class.java).findFirst().deleteFromRealm() -> 첫번째 데이터만 삭제
            }
        }
    }
}
