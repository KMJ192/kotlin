package com.example.grammar.AddView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.grammar.R

class AddView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        val carList = ArrayList<CarForList>()
        for(i in 0 until 10){
            carList.add(CarForList("$i 번째 car", "$i 순위 엔진"))
        }
        val container = findViewById<LinearLayout>(R.id.addview_container)
        //inflater -> xml로 정의된 view (또는 menu 등)를 실제 객체화 시키는 용도
        val inflater = LayoutInflater.from(this@AddView)
        for(i in 0 until carList.size){
            val itemView = inflater.inflate(R.layout.item_view, null)
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)

            carNameView.setText(carList.get(i).name)
            carEngineView.setText(carList.get(i).engine)
            container.addView(itemView)
        }
    }
}

class CarForList(val name:String, val engine:String){

}
