package com.example.grammar.Resource

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.grammar.R
import kotlinx.android.synthetic.main.activity_resource.*

class Resource : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        //1. res/values/strings.xml
        val ment = resources.getString(R.string.hello)
        Log.d("ment", "ment : $ment")

        //2. res/values/strings.xml
        val ment2 = getString(R.string.hello)
        Log.d("ment", "ment : $ment2")

        //3. res/values/colors.xml
        val color = getColor(R.color.textview_color)
        Log.d("color", "color : $color")

        button.setBackgroundColor(getColor(R.color.textview_color))
    }
}
