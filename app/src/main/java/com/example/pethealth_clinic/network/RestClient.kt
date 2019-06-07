package com.example.pethealth_clinic.network

import com.androidnetworking.interceptors.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class RestClient {
    val webServices: PetHealthServices

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(MAX_TIME.toLong(), TimeUnit.SECONDS)
            .connectTimeout(MAX_TIME.toLong(), TimeUnit.SECONDS).addInterceptor(interceptor).build()

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()

        webServices = retrofit.create(PetHealthServices::class.java)
    }



    companion object {
        private val BASE_URL = EndpointUrls.BASE_URL
        private val MAX_TIME = 300
    }
}
