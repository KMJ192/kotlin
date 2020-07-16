package com.example.grammar.ListView

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.grammar.AddView.CarForList
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val carList = ArrayList<CarForList>()
        for(i in 0 until 10){
            carList.add(CarForList("$i 번째 차, ", "$i 번째 엔진"))
        }

        val adapter = ListViewAdapter(carList, this@ListView)
        listView.adapter = adapter
        listView.setOnItemClickListener{parent, view, position, id ->
            //position에 해당하는 리스트
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this@ListView, "$carName $carEngine", Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(val carForList: ArrayList<CarForList>, val context : Context) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)

        val view: View
        lateinit var holder : ViewHolder

        if(convertView == null){
            //스크롤을 내리기 전 처음 보이는 화면의 List를 그려줌 // null에서 그려줌
            view = layoutInflater.inflate(R.layout.item_view, null)
            holder = ViewHolder()

            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)

            view.tag = holder
        }else{
            //스크롤을 내리면 위의 List를 아래로 재활용 // 재사용
            holder = convertView.tag as ViewHolder
            view = convertView
        }
        holder.carName?.setText(carForList[position].name)
        holder.carEngine?.setText(carForList[position].engine)

        return view
    }
    
    //List중 하나(position에 해당하는 List)
    override fun getItem(position: Int): Any {
        return carForList[position]
    }

    //해당 position의 id부여, position을 id로 설정
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //List의 갯수 반환
    override fun getCount(): Int {
        return carForList.size
    }
}

class ViewHolder{
    var carName : TextView? = null
    var carEngine : TextView? = null
}