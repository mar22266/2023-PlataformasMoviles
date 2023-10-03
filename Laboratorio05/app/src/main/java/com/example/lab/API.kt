package com.example.lab

import retrofit2.Call
import retrofit2.http.GET


interface myapi {
    @GET("posts")
    fun getmodels(): Call<List<model?>?>?
}
