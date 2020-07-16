package com.example.grammar.Network1

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grammar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)


        //http://mellowcode.org/json/students
        //http://mellowcode.org/test/code
        val retrofit = Retrofit.Builder()//Builder패턴
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        
        //Queue -> 대기줄
        //enqueue -> 대기줄에 service를 올려 놓음
        //GET요청
        //RetrofitService 인터페이스의 GET방식 function 호출
        service.getStudentList().enqueue(object: Callback<ArrayList<PersonFromServer>>{
            //통신이 실패할 경우 호출되는 function
            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                call.isCanceled
                call.isExecuted
                call.cancel()
            }

            //통신이 성공할 경우 호출되는 function
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                //isSuccessful -> Status가 200번대([200..300))
                //통신에 성공 할 경우 들어온 Data를 원하는 Datatype으로 casting
                if(response.isSuccessful){
                    val personList = response.body()

                    val code = response.code()

                    val error = response.errorBody()
                    val header = response.headers()
                }
            }
        })

        //POST요청 (1) //HashMap을 사용
        //RetrofitService 인터페이스의 POST방식 function 호출
//        val params = HashMap<String, Any>()   // <- HashMap을 사용
//        params.put("name", "이름")
//        params.put("age", 20)
//        params.put("intro", "Hi")
//        service.createStudent(params).enqueue(object : Callback<PersonFromServer>{
//            //통신이 실패할 경우 호출되는 function
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//
//            }
//
//            //통신이 성공할 경우 호출되는 function
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if(response.isSuccessful){
//                    val person = response.body()
//                }
//            }
//        })

        //POST요청 (2) //객체를 사용
        val person = PersonFromServer(name ="이름", age = 20, intro = "Hi") // <- 객체를 사용
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer>{
            //통신이 실패할 경우 호출되는 function
            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {

            }

            //통신이 성공할 경우 호출되는 function
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if(response.isSuccessful){
                    val person = response.body()
                }
            }
        })
    }
}

//Retrofit 생성
//1. build.gradle
//      implementation 'com.squareup.retrofit2:retrofit:2.3.0'
//      implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
//2. retrofit 생성
//      Retrofit.Bulider().baseUrl("베이스URL입력").addConverterFactory(GsonConverterFactory.create()).bulid()
//3. 베이스URL의 뒷부분URL을 관리할 Interface 생성 -> RetrofitService
//      @GET("베이스URL의 뒷부분")
//      함수 생성 fun getStudentList(): Call<ArrayList<PersonFromServer>>
//      import Call -> retrofit2.Call
//4. service 생성
//      RetrofitService interface를 retrofit에 연결 시키기 위함
//      retrofit.create(RetrofitService::class.java)

