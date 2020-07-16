package com.example.grammar.Network1

import java.io.Serializable

class PersonFromServer(
    var id :Int? = null,
    var name : String? = null,
    var age: Int? = null,
    var intro: String? = null
) : Serializable // => 직렬화, 상속 x | Implement o

//Json 형식 => List로 입력
//[
//    {
//      "id":0,
//      "name":"",
//      "age":0,
//      "intor":""
//    }
//]