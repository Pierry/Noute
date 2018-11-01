package com.github.pierry.noute.core.api.incoming

data class NoteRequest(
        val title: String,
        val content: String,
        val userId: String,
        val updatedAt: Long
)