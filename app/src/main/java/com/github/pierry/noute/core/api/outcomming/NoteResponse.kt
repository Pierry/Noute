package com.github.pierry.noute.core.api.outcomming

data class NoteResponse(
        val title: String,
        val content: String,
        val userId: String,
        val updatedAt: Long,
        val _id: String
)