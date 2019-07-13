package com.example.hokkung.cardemo.repostitory

import android.annotation.SuppressLint
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.model.Shop
import com.example.hokkung.cardemo.model.Type
import com.example.hokkung.cardemo.remote.ShopApis
import io.reactivex.Observable
import java.util.*

class CarShopRepository(val remote: ShopApis) {

    private val tag by lazy { this.javaClass.simpleName }

    @SuppressLint("CheckResult")
    fun getAllCategories(): Observable<List<Category>> {
        return remote.getAllCategories()
    }

    fun getAllTypes(): List<Type> = Arrays.asList(
        Type(id = 0, name = "car", image = R.drawable.ic_car, isCheck = false),
        Type(id = 1, name = "motorcycle", image = R.drawable.ic_motor_cycle, isCheck = false)
    )

    fun getAllShop(): Observable<List<Shop>> {
        return remote.getAllShops()
    }

    fun mockFavouriteShop(): Observable<List<Shop>>{
        return Observable.fromCallable { Arrays.asList(
            Shop(id = 1, name = "test1"),
            Shop(id = 2, name = "test2")
        )
        }
    }

    fun getAllFavouriteShop(): Observable<List<Shop>> {
        return mockFavouriteShop()
    }

}