package com.d121211008.brown.data

import com.d121211008.brown.data.repository.BrownRepository
import com.d121211008.brown.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val brownRepository: BrownRepository
}

class DefaultAppContainer: AppContainer {

    private val URL = "https://pixabay.com"

    private val retrofit = Retrofit.Builder()
//        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val brownRepository: BrownRepository
        get() = BrownRepository(retrofitService)
}