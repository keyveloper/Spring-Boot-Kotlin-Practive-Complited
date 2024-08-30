package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.dto.LikeRequestDto
import com.example.bulletinkotlin.entity.Board

interface BoardQueryDslRepository {
    fun findByLike(request: LikeRequestDto) : List<Board>
    fun findByCommentLike(request: LikeRequestDto): List<Board>
}