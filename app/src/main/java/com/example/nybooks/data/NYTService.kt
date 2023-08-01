package com.example.nybooks.data

import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTService {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "IDCt4cnlWh79cDJL8cOKe9tA0Nke7kUT",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}