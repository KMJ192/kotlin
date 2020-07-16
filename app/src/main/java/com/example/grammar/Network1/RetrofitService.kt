package com.example.grammar.Network1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    //annotation get방식
    @GET("json/students/") //baseURL의 뒷부분
    fun getStudentList(): Call<ArrayList<PersonFromServer>> //Call -> import retrofit2.Call
    
    //annotation post방식
    @POST("json/students/")
    fun createStudent(
        //Any -> 모든 타입 허용
        @Body params : HashMap<String, Any> //HashMap으로 Key Value값을 전달
    ) : Call<PersonFromServer>/*return 타입*/
    
    @POST
    fun createStudentEasy(
        @Body person : PersonFromServer //PersonFromServer객체를 전달
    ) : Call<PersonFromServer>
}