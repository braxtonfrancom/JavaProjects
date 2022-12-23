package com.braxtonfrancom

import java.io.File


fun plusThings(left: Int, right: Int): Int {
    return left + right;
}

fun Int.square(): Int = this * this



public data class Point(val x: Int, val y: Int)  //THIS IS A FULL CLASS! WITH a constructor!

operator fun Point.times(other: Point): Point = Point(this.x * other.y, this.y * other.x)

fun repeat(times: Int, action: ()->Unit) {
    var iter = 0
    while(iter < times) {
        action()
        iter++
    }
}

fun main(args: Array<String>) {
//    val name = readLine();
//    val x: Int = 3
//    val y: Double = 2.3
//    val z: String = "asdf"
//
//    println("Hello World")
//    println("Hello $name!")

    repeat(6) {
        println("here-here!") }

    val x = listOf(1, 2, 3)
    val y = setOf(4, 5, 6)
    val z = mapOf("aasd" to 1, "fdas" to 2)

    println(listOf(1,2,3,4,5,6).map { it * 2 })

    println(plusThings(5, 6))

    println(4.square())
    println(34 + 4)     //== prinln(32.plus(4));


    println(File("test.txt").readText())     //or writeText()


    while(true) {
        val x = readLine()
        println(x)
    }

}