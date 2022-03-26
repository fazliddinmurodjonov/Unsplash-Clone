package com.example.retrofit.retrofit

object Common {
    const val BASE_URL = "https://api.unsplash.com"
    const val API_KEY = "w2cNLAGA5Y0fPChBomGozA_YZ9VDgcM0TTZyBeOi-qM"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)

}