package com.example.hokkung.cardemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hokkung.cardemo.model.Shop
import com.example.hokkung.cardemo.remote.ShopApis
import com.example.hokkung.cardemo.repostitory.CarShopRepository
import io.reactivex.schedulers.Schedulers


class MainMenuViewModel(application: Application) : AndroidViewModel(application) {
    private val remote = ShopApis.call()
    private val repository: CarShopRepository = CarShopRepository(remote)
    private val shopMutableLiveData: MutableLiveData<List<Shop>> = MutableLiveData()
    private val favouriteShopMutableLiveData: MutableLiveData<List<Shop>> = MutableLiveData()


    fun getAllShop(): LiveData<List<Shop>> {
        repository.getAllShop()
            .subscribeOn(Schedulers.io())
            .subscribe{
                shopMutableLiveData.postValue(it)
            }
        return shopMutableLiveData
    }

    fun getAllFavouriteShop(): LiveData<List<Shop>> {
        repository.getAllFavouriteShop()
            .subscribeOn(Schedulers.io())
            .subscribe {
                favouriteShopMutableLiveData.postValue(it)
            }
        return favouriteShopMutableLiveData
    }

}