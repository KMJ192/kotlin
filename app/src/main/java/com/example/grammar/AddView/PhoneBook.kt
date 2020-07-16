package com.example.grammar.AddView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.grammar.R

class PhoneBook : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book)

        val phoneBook = createFakePhonebook(30, PhoneBook0())
        createPhoneBookList(phoneBook)
    }

    fun createFakePhonebook(fakeNumber : Int, phoneBook : PhoneBook0) : PhoneBook0{
        for(i in 0 until fakeNumber){
            phoneBook.addPerson(Person(name = ""+i+"번째 사람", number = ""+i+"번째 PhoneNumber"))
        }
        return phoneBook
    }
    fun createPhoneBookList(phoneBook: PhoneBook0){
        val layoutInflater = LayoutInflater.from(this@PhoneBook)
        val container = findViewById<LinearLayout>(R.id.phonebook_list_container)
        for(i in 0 until phoneBook.personList.size){
            val view = layoutInflater.inflate(R.layout.phonebook_item, null)
            val personNameView = view.findViewById<TextView>(R.id.person_name)
            personNameView.setText(phoneBook.personList.get(i).name)
            addSetOnClickListener(phoneBook.personList.get(i), view)
            container.addView(view)
        }
    }
    fun addSetOnClickListener(person:Person, view: View){
        view.setOnClickListener{
            val intent = Intent(this@PhoneBook, PhoneBookDetail::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("number", person.number)
            startActivity(intent)
        }
    }
}

class PhoneBook0(){
    //전화 번호부
    val personList = ArrayList<Person>()
    fun addPerson(person : Person){
        personList.add(person)
    }
}

class Person(val name:String, var number:String){

}