package com.example.grammar.Network1

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grammar.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(
            recycler_person,
            LayoutInflater.from(this@NetworkActivity)
        ).execute()
    }
}


//Network 클래스를 만들기 위해 Async를 이용한 비동기 처리
//onPostExecute => MainThread
//doInBackground => SubThread
class NetworkTask(
    //생성자
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater
) : AsyncTask<Any?, Any?, Array<PersonFromServer>?>(){
    //Main Thread에서 실행되는 onPostExecute / UI에 접근 
    //View를 그릴때 onPostExecute에서 실행 하여야 함
    override fun onPostExecute(result: Array<PersonFromServer>?) {
        val adapter = PersonAdapter(result!!, inflater)
        recyclerView.adapter = adapter
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer> {
        val urlString : String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }
        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
        return data
    }
}

class PersonAdapter(
    val personList : Array<PersonFromServer>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView
        val age : TextView
        val intro : TextView

        init{
            name = itemView.findViewById(R.id.person_name)
            age = itemView.findViewById(R.id.person_age)
            intro = itemView.findViewById(R.id.person_ment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        holder.name.setText(personList.get(position).name ?: "")
        holder.age.setText(personList.get(position).age.toString())
        holder.intro.setText(personList.get(position).intro ?: "")
    }
}

//Adapter class생성
//1. Adapter DataType : RecyclerView.Adater<뷰홀더(3번에서 생성한 뷰홀더)>
//2. Adapter Parameter ==> List : Array<클래스이름>, inflater : LayoutInflater
//3. inner class ViewHolder 생성(제너릭 타입 => itemView : View) : RecyclerView.ViewHolder(itemView => ViewHolder의 Parameter)
//4. init(최초 화면에 찍힐 id를 받아옴 => 초기화)
//5. onCreateViewHolder, getItemCount, onBindViewHolder => override 생성
//6. getItemCount => 리스트의 크기를 return
//7. onCreateViewHolder => inflater를 생성하여 3번에서 생성한 ViewHolder()에 parameter로 return
//8. onBindeViewHolder => 리스트의 tag(id)를 설정