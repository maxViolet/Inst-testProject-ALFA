package com.android.insta_testproject_alfa.network

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val API_KEY = ""
    private val FIREBASE_URL = ""
    private val TIMEOUT_IN_SECONDS = 2

    object Factory {
        fun getInastance(): RetrofitClient {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //todo base url
                .baseUrl("")
                .build()

            return retrofit.create(RetrofitClient::class.java)
        }
    }

}