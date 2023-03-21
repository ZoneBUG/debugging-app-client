package com.zonebug.debugging.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://15.164.56.217/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val api = retrofit.create(RetrofitService::class.java);

    fun getInstance() : RetrofitService? {
        return api;
    }

}