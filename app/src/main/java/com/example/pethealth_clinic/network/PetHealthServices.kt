package com.example.pethealth_clinic.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PetHealthServices {
    @POST(EndpointUrls.LOGIN)
    fun login(@Body requestBody: JsonObject): Call<RestView<JsonObject>>

    @POST(EndpointUrls.APPOINTMENTS)
    fun getAppts(@Header("access_token") accessToken: String?, @Path("user_id") userId: Int?): Call<RestView<JsonArray>>
}