package com.example.pethealth_clinic.network;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RestClient {
    private final PetHealthServices webServices;
    private static final String BASE_URL = EndpointUrls.BASE_URL;
    private static final int MAX_TIME = 300;

    public RestClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(MAX_TIME, TimeUnit.SECONDS).
                connectTimeout(MAX_TIME, TimeUnit.SECONDS).
                addInterceptor(interceptor).
                build();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient).
                build();

        webServices = retrofit.create(PetHealthServices.class);
    }

    public PetHealthServices getWebServices(){return  webServices;}
}
