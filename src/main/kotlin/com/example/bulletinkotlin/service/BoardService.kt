package com.example.bulletinkotlin.service

import com.example.bulletinkotlin.dto.GetBoardResultDto
import com.example.bulletinkotlin.dto.GetCommentResultDto
import com.example.bulletinkotlin.entity.Board
import com.example.bulletinkotlin.entity.Comment
import com.example.bulletinkotlin.repository.BoardRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository) {
    @Transactional
    fun findBoards() : List<GetBoardResultDto> = boardRepository.findAll().map { convertToResult(it) }

    fun findBoardById(id : Long) : GetBoardResultDto? {
        val board = boardRepository.findById(id).orElse(null)
        return board?.let { convertToResult(it) }
    }

    fun deleteBoardById(id: Long) = boardRepository.deleteById(id)

//    fun writeBoard(board: Board) : String = boardRepository.save(board)
    fun convertCommentToResult(comment: Comment): GetCommentResultDto {
        return GetCommentResultDto(
            id = comment.id!!,
            boardId = comment.board.id!!,
            writer = comment.writer,
            textContent = comment.textContent,
            firstWritingTime = comment.firstWritingTime,
            lastModifiedTime = comment.lastModifiedTime)
    }

    fun convertToResult(board: Board): GetBoardResultDto{
        val commentResults: List<GetCommentResultDto> = board.comments.map { convertCommentToResult(it) }
        return GetBoardResultDto(
            id = board.id!!,
            title = board.title,
            writer =  board.writer,
            textContent = board.textContent,
            firstWritingTime = board.firstWritingTime,
            lastModifiedTime = board.lastModifiedTime,
            readingCount = board.readingCount,
            comments = commentResults)
    }
}