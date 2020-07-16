package com.example.grammar.Kotlin


//Iterator

fun main(args: Array<String>){
    val a = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,0)

    //1. for문_1
    //List a를 출력하는 반복문
    for(i in a){
        println(i)
    }
    println("============================================================")
    //2. for문_2
    //List a의 Index와 함께 출력
    for((index, i) in a.withIndex()){
        println("index : $index, value : $i")
    }
    println("============================================================")
    //3. forEach문_1
    //List a를 순회하는 forEach문
    a.forEach{
        println(it)
    }
    println("============================================================")
    //4. forEach문_2
    //forEach안에서 변수를 사용해야 할 경우
    a.forEach{i ->
        println(i)
    }
    println("============================================================")
    //5. forEachIndexed
    //Index와 함께 출력하는 index문
    a.forEachIndexed{ index, i ->
        println("index : $index, value : $i")
    }
    println("============================================================")
    //6. for문_3
    //마지막으로 포함하지 않는 until 옵션
    for(i in 0 until a.size){
        println(a[i])
    }
    println("============================================================")
    //7. for문_4
    //건너뛰기 기능을 있는 step
    for(i in 0 until a.size step(2)){
        println(a[i])
    }
    println("============================================================")
    //8. for문_5
    //역순으로 반복하는 downTo 옵션
    for(i in a.size - 1 downTo(0)){
        println(a[i])
    }
    println("============================================================")
    //9. for문_6
    //여러가지 옵션 중복 적용 가능
    for(i in a.size - 1 downTo(0) step(2)){
        println(a[i])
    }
    println("============================================================")
    //10. for문_7
    //마지막을 포함하는 ".."
    for(i in 0 .. a.size - 1){
        println(a[i])
    }
    println("============================================================")
    //11. while문_1
    var x: Int = 0
    var y: Int = 10
    while(x < y){
        x++
        println(x)
    }
    println("============================================================")
    //12. do ~ while문
    do{
        x++
        println(x)
    }while(x < y)

}