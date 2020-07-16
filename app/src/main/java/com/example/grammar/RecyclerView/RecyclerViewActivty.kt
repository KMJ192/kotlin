package com.example.grammar.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grammar.AddView.CarForList
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_recycler_view_activty.*

class RecyclerViewActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_activty)

        val carList = ArrayList<CarForList>()
        for(i in 0 until 50){
            carList.add(CarForList("$i 번째 차량", "$i 번째 엔진"))
        }
        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this@RecyclerViewActivty))
        recycler_view.adapter = adapter

        //1. LinearLayoutManager(가로로 일렬)
        //recycler_view.layoutManager = LinearLayoutManager(this@RecyclerViewActivty)
        //2. GridLayout(가로로 두줄)
        recycler_view.layoutManager = GridLayoutManager(this@RecyclerViewActivty,2)
    }
}

//RecyclerViewAdapter생성(Parameter -> List를 가져오는 ArrayList, inflater : LayoutInflater) : (상속) RecyclerView.Adapter<뷰홀더>()
class RecyclerViewAdapter(val itemList : ArrayList<CarForList>, val inflater : LayoutInflater) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    //inner키워드 입력
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val carName : TextView
        val carEngine : TextView

        init{
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
            //itemView = item_view의 inflater
            itemView.setOnClickListener{
                val position = adapterPosition
                //innerClass(ViewHolder)는 OutterClass에 접근 할 수 없음
                //innerClass에 inner키워드 입력
                val engineName = itemList.get(position).engine
                Log.d("engine", engineName)
            }
        }
    }

    //View를 그림
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }
    //List의 Size
    override fun getItemCount(): Int {
        return itemList.size
    }
    //List의 Tag 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.setText(itemList.get(position).name)
        holder.carEngine.setText(itemList.get(position).engine)
    }
}