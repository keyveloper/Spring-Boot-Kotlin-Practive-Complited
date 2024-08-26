package com.example.bulletinkotlin.controller

import com.example.bulletinkotlin.dto.GetBoardResponseDto
import com.example.bulletinkotlin.dto.GetBoardResultDto
import com.example.bulletinkotlin.dto.GetCommentResponseDto
import com.example.bulletinkotlin.dto.GetCommentResultDto
import com.example.bulletinkotlin.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(private val boardService: BoardService) {
    @GetMapping("/boards")
    fun findBoards(): ResponseEntity<List<GetBoardResponseDto>> {
        val responses: List<GetBoardResponseDto> = boardService.findBoards()
            .map { convertResponse(it) }
        return ResponseEntity.ok().body(responses)
    }

    fun convertCommentToResponse(commentResult: GetCommentResultDto): GetCommentResponseDto {
        return GetCommentResponseDto(
            id = commentResult.id,
            writer = commentResult.writer,
            textContent = commentResult.textContent,
            firstWritingTime = commentResult.firstWritingTime,
            lastModifiedTime = commentResult.lastModifiedTime)
    }

    fun convertResponse(result: GetBoardResultDto): GetBoardResponseDto {
        val commentResponses: List<GetCommentResponseDto> = result.comments.map { convertCommentToResponse(it) }
        return GetBoardResponseDto(
            id = result.id,
            title = result.title,
            writer = result.writer,
            textContent = result.textContent,
            firstWritingTime = result.firstWritingTime,
            lastModifiedTime = result.lastModifiedTime,
            readingCount = result.readingCount,
            comments = commentResponses)
    }
}