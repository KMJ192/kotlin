package com.example.grammar.RecyclerView

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grammar.AddView.Person
import com.example.grammar.AddView.PhoneBook0
import com.example.grammar.AddView.PhoneBookDetail
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_phone_book_with_recycler_view.*

class PhoneBookWithRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_with_recycler_view)

        val phoneBook = createFakePhoneBook(faskNumber = 30)
        val phoneBookRecyclerAdapter = PhoneBookRecyclerAdapter(
            phoneBookList = phoneBook,
            inflater = LayoutInflater.from(this@PhoneBookWithRecyclerViewActivity),
            activity = this@PhoneBookWithRecyclerViewActivity
        )
        with(phonebook_recycler_view){
            this.adapter = phoneBookRecyclerAdapter
            //LinearLayoutManager 수직으로 그림
            this.layoutManager = LinearLayoutManager(this@PhoneBookWithRecyclerViewActivity)
        }
    }
    fun createFakePhoneBook(faskNumber: Int = 10, phoneBook : PhoneBook0 = PhoneBook0()) : PhoneBook0 {
        for(i in 0 until faskNumber){
            phoneBook.addPerson(
                Person(name = "$i'st Person", number = "$i'st Person's PhoneNumber")
            )
        }
        return phoneBook
    }
}

class PhoneBookRecyclerAdapter(
    val phoneBookList : PhoneBook0,
    val inflater : LayoutInflater,
    val activity : Activity//Intent에서 Activity가 필요하기 때문에 Activity를 상속받아서 startActivity를 사용할 수 있도록 함
): RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val personName : TextView
        init{
            personName = itemView.findViewById(R.id.person_name)
            itemView.setOnClickListener{
                val intent = Intent(activity, PhoneBookDetail::class.java)
                intent.putExtra("number", phoneBookList.personList[adapterPosition].number)
                activity.startActivity(intent)
                intent.putExtra("name", phoneBookList.personList[adapterPosition].name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.phonebook_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneBookList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.setText(phoneBookList.personList[position].name)
    }
}
