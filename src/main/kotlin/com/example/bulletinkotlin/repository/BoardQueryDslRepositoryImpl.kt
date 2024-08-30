package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.dto.LikeRequestDto
import com.example.bulletinkotlin.entity.Board
import com.example.bulletinkotlin.entity.QBoard
import com.example.bulletinkotlin.entity.QComment
import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory


class BoardQueryDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory
): BoardQueryDslRepository {
    private val board = QBoard.board

    override fun findByLike(request: LikeRequestDto) : List<Board> {
        val booleanBuilder = BooleanBuilder()

        request.writer?.let {
            booleanBuilder.and(board.writer.like(request.writer))
        }

        request.notContainWriter?.let {
            booleanBuilder.and(board.writer.notLike(request.notContainWriter))
        }

        request.textContent?.let {
            booleanBuilder.and(board.textContent.like(request.textContent))
        }

        return queryFactory.selectFrom(QBoard.board)
            .where(booleanBuilder)
            .fetch()
    }

    override fun findByCommentLike(request: LikeRequestDto): List<Board> {
        val comment: QComment = QComment.comment
        val booleanBuilder = BooleanBuilder()

        request.writer?.let {
            booleanBuilder.and(comment.writer.like(request.writer))
        }

        request.notContainWriter?.let {
            booleanBuilder.and(comment.writer.notLike(request.notContainWriter))
        }

        request.textContent?.let {
            booleanBuilder.and(comment.textContent.like(request.textContent))
        }

        return queryFactory.selectDistinct(board)
            .from(comment)
            .join(comment.board(), board)
            .where(booleanBuilder)
            .fetch()
    }
}