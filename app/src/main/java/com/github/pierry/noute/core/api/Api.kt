package com.github.pierry.noute.core.api

import com.github.pierry.noute.core.api.incoming.NoteRequest
import com.github.pierry.noute.core.api.incoming.UserRequest
import com.github.pierry.noute.core.api.outcomming.NoteResponse
import com.github.pierry.noute.core.api.outcomming.UserResponse
import retrofit2.http.*

interface Api {

  @POST("/user")
  fun addUser(@Body userRequest: UserRequest): UserResponse

  @POST("/login")
  fun login(@Body userRequest: UserRequest): UserResponse

  @POST("/notes")
  fun addNote(@Body noteRequest: NoteRequest): UserRequest

  @GET("/notes/all/{id}")
  fun getAllNotesByUserId(@Path("id") id: String): NoteResponse

  @GET("/notes/{id}")
  fun getByNoteId(@Path("id") id: String): NoteResponse

  @PUT("/notes/{id}")
  fun updateNoteById(@Path("id") id: String, @Body noteRequest: NoteRequest)

  @DELETE("/notes/{id}")
  fun deleteById(@Path("id") id: String)

}