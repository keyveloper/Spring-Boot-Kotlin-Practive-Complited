package com.example.bulletinkotlin.dto

import com.example.bulletinkotlin.enum.DeleteStatus

class DeleteResultDto(
    var id: Long,
    var status: DeleteStatus
)