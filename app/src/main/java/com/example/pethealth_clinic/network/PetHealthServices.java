package com.example.pethealth_clinic.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PetHealthServices {
    @POST(EndpointUrls.LOGIN)
    Call<RestView<JsonObject>> login(@Body JsonObject requestBody);

    @POST(EndpointUrls.APPOINTMENTS)
    Call<RestView<JsonArray>> getAppts(@Header("access_token") String accessToken, @Path("user_id") int userId);
}
