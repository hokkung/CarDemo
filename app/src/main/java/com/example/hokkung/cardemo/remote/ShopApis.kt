package com.example.hokkung.cardemo.remote

import androidx.lifecycle.LiveData
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.model.Shop
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ShopApis {
    @GET("posts/")
    fun getAllShops(): Observable<List<Shop>>

    @GET("users")
    fun getAllCategories(): Observable<List<Category>>

    companion object {
        fun call(): ShopApis {
            val BASE_URL = "https://jsonplaceholder.typicode.com/"

            val retrofit =  Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ShopApis::class.java)
        }
    }
}