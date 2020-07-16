package com.example.grammar.Kotlin

fun main(args: Array<String>){

    //// Immutable Collection (변경이 불가능한 Collection)

    println("1. Immutable Collection")

    //List
    val numberList = listOf<Int>(1, 2, 3, 4, 5)
    println(numberList)

    println("=========================================")

    //Set
    //중복된 값은 저장하지 않는 Set, 중복을 허용하지 않음
    val numberSet = setOf<Int>(1,2,3,3,3)
    println(numberSet)
    println(numberSet.size)

    println("=========================================")

    //Map
    //Key, Value형태
    val numberMap = mapOf<String, Int>("one" to 1, "two" to 2, "three" to 3)
    println(numberMap["one"]) //1을 얻기 위해 "one"을 입력

    println("=========================================")

    ////Mutable Collection 변경이 가능한 Collection
    println("2. Mutable Collection")
    val mNumberList = mutableListOf<Int>(1,2,3)
    mNumberList.add(3,4) //List에 값을 입력
    mNumberList.set(0, 0) // List의 값을 변경
    mNumberList.removeAt(1)//List의 값을 제거
    println(mNumberList)

    println("=========================================")

    val mNumberSet = mutableSetOf<Int>(1,2,3,4,4,4)
    mNumberSet.add(10)//Set에 값을 입력
    mNumberSet.remove(3) //Set의 값을 제거(없는 값을 제거하려고 해도 에러는 나지 않음)
    println(mNumberSet)

    println("=========================================")

    val mNumberMap = mutableMapOf<String, Int>("one" to 1, "two" to 2, "three" to 3)
    mNumberMap.put("four", 4) //Map에 값을 입력
    mNumberMap.replace("two", 3)//Map의 값을 변경함
    println(mNumberMap.keys) // Map의 키만 출력
    println(mNumberMap.values) // Map의 값만 출력
    println(mNumberMap)
    mNumberMap.clear() //Map의 값을 모두 제거
}