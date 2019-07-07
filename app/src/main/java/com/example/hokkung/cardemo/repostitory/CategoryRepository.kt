package com.example.hokkung.cardemo.repostitory

import android.annotation.SuppressLint
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.model.Type
import com.example.hokkung.cardemo.remote.ShopApis
import io.reactivex.Observable
import java.util.*

class CategoryRepository(val remote: ShopApis) {

    private val tag by lazy { this.javaClass.simpleName }

    @SuppressLint("CheckResult")
    fun getAllCategories(): Observable<List<Category>> {
        return remote.getAllCategories()
    }

    fun getAllTypes(): List<Type> = Arrays.asList(
        Type(id = 0, name = "car", image = R.drawable.ic_car, isCheck = false),
        Type(id = 1, name = "motorcycle", image = R.drawable.ic_motor_cycle, isCheck = false)
    )
}