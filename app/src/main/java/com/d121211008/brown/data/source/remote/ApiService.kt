package com.d121211008.brown.data.source.remote

import com.d121211008.brown.data.response.GetBrownResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    https://pixabay.com/api/?key=41249929-965ed05871767e5081c2a3b07&q=brown&image_type=photo&pretty=true
    @GET("api/")
    suspend fun getBrown(
        @Query("key") key:String = "41249929-965ed05871767e5081c2a3b07",
        @Query("q") q:String = "brown",
        @Query("image_type") image_type:String = "photo",
        @Query("pretty") pretty:String = "true"
    ): GetBrownResponse
}