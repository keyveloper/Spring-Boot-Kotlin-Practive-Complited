package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

}