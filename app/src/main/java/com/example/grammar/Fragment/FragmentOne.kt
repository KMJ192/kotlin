package com.example.grammar.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.grammar.R
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment(){
    
    //   fragment에서 Activity로 data 전달 예시
    //1. Interface 구현
    //2. Interface타입 변수 선언
    //3. onAttach에서 context를 Interface타입으로 Casting
    //4. onViewCreated에 Listener선언
    //5. Listener에서 interface변수.onDataPass(전달할 데이터) 입력
    //6. Activity에서 Fragment에서 생성한 Interface 참조
    //7. Fragment의 onDataPass함수를 받음

    //1. Interface 구현 -> Interface타입 변수 선언
    interface OnDataPassListener {
        fun onDataPass(data: String?)
    }

    //var arguments: Bundle
    lateinit var dataPassListener : OnDataPassListener


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("life_cycle", "F onAttach")
        context?.let { super.onAttach(it) }
        //3. onAttach에서 context를 Interface타입으로 Casting
        dataPassListener = context as OnDataPassListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("life_cycle", "F onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("life_cycle", "F onCreateView")
        //Fragment가 Interface를 처음으로 그릴 때 호출됨
        //inflater -> View를 그려줌 return type은 view
        //container -> Parent View
        return inflater.inflate(R.layout.fragment_one,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("life_cycle", "F onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        //Fragment는 onViewCreated에 작성
        //4. onViewCreated에 Listener선언
        pass.setOnClickListener {
            //5. Listener에서 interface변수.onDataPass(전달할 데이터) 입력
            dataPassListener.onDataPass("data")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("life_cycle", "F onActivityCreated")

        val data = arguments?.getString("Key") //FragmentActivity로 부터 데이터를 전달 받음
        Log.d("data", data)

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("life_cycle", "F onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("life_cycle", "F onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("life_cycle", "F onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("life_cycle", "F onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("life_cycle", "F onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d("life_cycle", "F onDetach")
        super.onDetach()
    }
}