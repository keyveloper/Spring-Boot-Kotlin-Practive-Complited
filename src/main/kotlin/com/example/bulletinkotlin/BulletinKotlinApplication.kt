package com.example.bulletinkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BulletinKotlinApplication

fun main(args: Array<String>) {
    runApplication<BulletinKotlinApplication>(*args)
    println("this is master")
}
