package com.example.bulletinkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class BulletinKotlinApplication

fun main(args: Array<String>) {
    runApplication<BulletinKotlinApplication>(*args)
}
