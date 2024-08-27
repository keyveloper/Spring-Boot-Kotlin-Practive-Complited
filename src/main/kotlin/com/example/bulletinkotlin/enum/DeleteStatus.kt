package com.example.bulletinkotlin.enum

enum class DeleteStatus(val message: String) {
    SUCCESS("Delete Success"),
    FAILED("Not exist id")
}