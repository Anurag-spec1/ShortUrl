package com.anurag.shorturl.services

import com.anurag.shorturl.model.UrlRequest
import com.anurag.shorturl.model.UrlResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("shorten")
    fun sendUrl(@Body request: UrlRequest): Call<UrlResponse>

    @GET("redirect/{id}")
    fun redirectUrl(@Path("id") id: String): Call<Void>
}