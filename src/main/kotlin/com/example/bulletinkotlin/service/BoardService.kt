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

    fun findBoardById(id : Long) : Board? = boardRepository.findById(id).orElse(null)

    fun deleteBoardById(id: Long) = boardRepository.deleteById(id)

//    fun writeBoard(board: Board) : String = boardRepository.save(board)
    fun convertCommentToResult(comment: Comment): GetCommentResultDto {
        return GetCommentResultDto(comment.id, comment.board.id, comment.writer,
            comment.textContent, comment.firstWritingTime, comment.lastModifiedTime)
    }

    fun convertToResult(board: Board): GetBoardResultDto{
        val commentResults: List<GetCommentResultDto> = board.comments.map { convertCommentToResult(it) }
        return GetBoardResultDto(board.id, board.title, board.writer, board.textContent,
            board.firstWritingTime, board.lastModifiedTime, board.readingCount, commentResults)
    }

}