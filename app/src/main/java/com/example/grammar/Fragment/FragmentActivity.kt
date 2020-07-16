package com.example.grammar.Fragment

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import android.support.v4.app.FragmentManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_fragment.*

                                                //6. Activity에서 Fragment에서 생성한 Interface 참조
class FragmentActivity : AppCompatActivity(), FragmentOne.OnDataPassListener {

    //7. Fragment의 onDataPass함수를 받음
    override fun onDataPass(data: String?) {
        Log.d("pass", data.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life_cycle", "onCreate")
        setContentView(R.layout.activity_fragment)

        val fragmentOne : FragmentOne = FragmentOne()
        
        //Fragment에 Data를 입력하는 방법
        //bundle 생성 -> bundle에 데이터 입력 -> 데이터를 전달할 Activity에 bundle입력
        val bundle: Bundle = Bundle()
        bundle.putString("Key","hello")
        fragmentOne.arguments = bundle // fragmentOne에 할당
        

        button.setOnClickListener {
            //Fragment를 동적으로 작동하는 방법
            //Fragment를 붙이는 방법 replace/add
            val fragmentManager : FragmentManager = supportFragmentManager

            //Transaction
            //작업의 단위 -> 시작과 끝이 있음
            val fragmentTransaction = fragmentManager.beginTransaction() //시작
            fragmentTransaction.replace(R.id.container, fragmentOne)
            fragmentTransaction.commit()                                 //끝
            //commitnow -> 즉시 종료
            //commit    -> 종료할 수 있을때 종료
        }

        button2.setOnClickListener {
            // Fragment remove/detach 하는 방법
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.detach(fragmentOne)
            //fragmentTransaction.remove(fragmentOne)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        Log.d("life_cycle", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("life_cycle", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("life_cycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("life_cycle", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("life_cycle", "onDestroy")
        super.onDestroy()
    }
}
