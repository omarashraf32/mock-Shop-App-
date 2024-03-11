package com.example.data.remote

import com.example.domain.model.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProduct(): CategoryResponse
}