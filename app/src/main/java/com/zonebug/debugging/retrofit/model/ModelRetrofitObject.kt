package com.zonebug.debugging.retrofit.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ModelRetrofitObject {

    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://192.168.0.148:5000/")
        .baseUrl("http:///172.30.1.85:5000/")
        .client(okHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ModelRetrofitService::class.java)

    fun getInstance() : ModelRetrofitService? {
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

            val newRequest = request().newBuilder()
                .build()

            return chain.proceed(newRequest)
        }
    }

}