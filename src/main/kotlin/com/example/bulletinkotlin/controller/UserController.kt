package com.example.bulletinkotlin.controller

import com.example.bulletinkotlin.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

}