package com.d121211008.brown.data.repository

import com.d121211008.brown.data.response.GetBrownResponse
import com.d121211008.brown.data.source.remote.ApiService

class BrownRepository (private val  apiService: ApiService) {

    suspend fun getBrown(): GetBrownResponse {
        return apiService.getBrown()
    }
}