package com.example.bulletinkotlin.service

import com.example.bulletinkotlin.entity.Board
import com.example.bulletinkotlin.repository.BoardRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BoardService(private val boardRepository: BoardRepository) {
    fun getBoards() : List<Board> = boardRepository.findAll()

    fun getBoardById(id : Long) : Board? = boardRepository.findById(id).orElse(null)

    fun deleteBoardById(id: Long) = boardRepository.deleteById(id)

//    fun writeBoard(board: Board) : String = boardRepository.save(board)

}