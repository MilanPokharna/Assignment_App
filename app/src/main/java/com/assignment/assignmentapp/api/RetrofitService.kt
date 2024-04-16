package com.assignment.assignmentapp.api

import com.assignment.assignmentapp.models.CustomerResponse
import com.assignment.assignmentapp.models.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("posts")
    suspend fun getAllPosts(@Query ("userId") userId:Int ) : Response<List<Data>>

}