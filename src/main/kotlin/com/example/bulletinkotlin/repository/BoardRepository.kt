package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}