package com.example.bulletinkotlin.controller

import com.example.bulletinkotlin.dto.*
import com.example.bulletinkotlin.enum.DeleteStatus
import com.example.bulletinkotlin.enum.WriteStatus
import com.example.bulletinkotlin.service.BoardService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@Slf4j
@RestController
class BoardController(private val boardService: BoardService) {
    private val logger = LoggerFactory.getLogger(BoardController::class.java)
    @GetMapping("/boards")
    fun findBoards(): ResponseEntity<List<GetBoardListResponseDto>> {
        val responses: List<GetBoardListResponseDto> = boardService.findBoards()
            .map { convertListResponse(it) }
        return ResponseEntity.ok().body(responses)
    }

    @GetMapping("/board/{id}")
    fun findBoardById(@PathVariable id: Long): ResponseEntity<GetBoardResponseDto> {
        val boardResult = boardService.findBoardById(id)
        return if (boardResult != null) {
            ResponseEntity.ok().body(convertResponse(boardResult))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/board")
    fun writeBoard(
        @RequestParam title: String,
        @RequestParam writer: String,
        @RequestParam textContent: String
    ) : ResponseEntity<String>{
       val writeResult: WriteBoardResultDto = boardService.write(
           WriteBoardRequestDto(
           title = title,
           writer = writer,
           textContent = textContent
       )
       )
       if (writeResult.status == WriteStatus.SUCCESS) {
           return ResponseEntity.ok().body("ID: $writeResult.id ${writeResult.status.message}")
       }
        return ResponseEntity.badRequest().body(writeResult.status.message)
    }

    @DeleteMapping("/board/{id}")
    fun deleteBoard(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            val deleteResult: DeleteResultDto = boardService.deleteBoardById(id)

            if (deleteResult.status == DeleteStatus.SUCCESS) {
                ResponseEntity.ok().body("$id board ${deleteResult.status.message}")
            }
            ResponseEntity.badRequest().body("$id is ${deleteResult.status.message}")
        } catch (e: RuntimeException) {
            throw e
        }
    }

    @GetMapping("/boards/like")
    fun findByLike(
        @RequestParam(required = false) writer: String?,
        @RequestParam(required = false) notContainWriter: String?,
        @RequestParam(required = false) textContent: String?,
    ) : ResponseEntity<List<GetBoardListResponseDto>>{
        logger.info("writer: $writer, notContainWriter: $notContainWriter, textContent: $textContent")

        if (notContainWriter != null) {
            logger.info("notContainWriter is not null!! ")
        }

        val response: List<GetBoardListResponseDto> = boardService.findByLike(LikeRequestDto(
            writer = writer,
            notContainWriter = notContainWriter,
            textContent = textContent
        )).map { convertListResponse(it) }
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/boards/like-comment")
    fun findByLikeComment(
        @RequestParam(required = false) writer: String?,
        @RequestParam(required = false) notContainWriter: String?,
        @RequestParam(required = false) textContent: String?
    ) : ResponseEntity<List<GetBoardListResponseDto>>{
        val response: List<GetBoardListResponseDto> = boardService.findByCommentLike(
            LikeRequestDto(
                writer = writer,
                notContainWriter = notContainWriter,
                textContent = textContent
            )
        ).map { convertListResponse(it) }
        return ResponseEntity.ok().body(response)

    }

    fun convertCommentToResponse(commentResult: GetCommentResultDto): GetCommentResponseDto {
        return GetCommentResponseDto(
            id = commentResult.id,
            boardId = commentResult.boardId,
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

    fun convertListResponse(result: GetBoardListResultDto): GetBoardListResponseDto {
        return GetBoardListResponseDto(
            id = result.id,
            title = result.title,
            writer = result.writer,
            textContent = result.textContent,
            firstWritingTime = result.firstWritingTime,
            readingCount = result.readingCount,
        )
    }
}