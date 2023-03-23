package com.zonebug.debugging.retrofit

import com.zonebug.debugging.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitObject {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(AppInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://172.30.1.85:8080/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(RetrofitService::class.java)

    fun getInstance() : RetrofitService? {
        return api;
    }


    fun okHttpClient(interceptor: Interceptor) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response= with(chain) {
            val accessToken = App.prefs.getString("accessToken", "")

            val newRequest = request().newBuilder()
                .addHeader("Authorization", accessToken)
                .build()

            return chain.proceed(newRequest)
        }
    }

}