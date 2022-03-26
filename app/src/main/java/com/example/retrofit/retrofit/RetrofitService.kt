package com.example.retrofit.retrofit


import com.example.retrofit.model.ImageModel
import com.example.retrofit.model.SearchModel
import com.example.retrofit.retrofit.Common.API_KEY
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    //&page=1&per_page=31
    @Headers("Authorization: Client-ID $API_KEY")
    @GET("/photos")
    fun getNature(): Call<List<ImageModel>>

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("/search/photos")
    fun searchImages(
        @Query("query") query: String, @Query("page") page: Int, @Query("per_page") per_page: Int,
    ): Call<SearchModel>
}