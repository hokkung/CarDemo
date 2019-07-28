package com.example.hokkung.cardemo.viewmodel

import android.app.Application
import android.location.Address
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.model.Type
import com.example.hokkung.cardemo.remote.ShopApis
import com.example.hokkung.cardemo.repostitory.CarShopRepository
import io.reactivex.schedulers.Schedulers

class AddShopViewModel(application: Application) : AndroidViewModel(application) {

    private val tag by lazy { "AddShopViewModel" }
    private val remote = ShopApis.call()
    private val repository: CarShopRepository = CarShopRepository(remote)

    private val categoriesMutableLiveData: MutableLiveData<List<Category>> = MutableLiveData()
    private val typesLiveData: MutableLiveData<List<Type>> = MutableLiveData()

    init {
        fetchAllCategoryFromServer()
    }


    fun createCarShop(name: String, address: Address) {
        Log.v(tag, address.toString())
    }

    fun updateTypeCar(type: Type) {
        typesLiveData.value?.forEach {
            if (it.name == type.name) it.isCheck = type.isCheck
        }
        Log.v(tag, typesLiveData.value.toString())
    }


    fun updateCategories(category: Category) {
        categoriesMutableLiveData.value?.forEach {
            if (it.name == category.name) it.isChecked = category.isChecked
        }
        Log.v(tag, categoriesMutableLiveData.value.toString())
    }

    fun fetchAllCategoryFromServer() {
        repository.getAllCategories()
            .subscribeOn(Schedulers.io())
            .subscribe{
                categoriesMutableLiveData.postValue(it)
            }
    }

    fun getAllCategories(): LiveData<List<Category>> = categoriesMutableLiveData


    fun getAllTypes(init: Boolean = false): LiveData<List<Type>> {
        if (init) {
            typesLiveData.postValue(repository.getAllTypes())
        }
        return typesLiveData
    }


}