package com.example.optune.api

import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

object RetrofitInstance {
    val api: OfferApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // Emulator's localhost to host
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(OfferApi::class.java)
    }
}
