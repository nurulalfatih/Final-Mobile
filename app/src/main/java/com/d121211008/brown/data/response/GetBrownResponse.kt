package com.d121211008.brown.data.response

import com.d121211008.brown.data.model.Brown
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBrownResponse(
    @SerialName("hits")
    val hits: List<Brown>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)