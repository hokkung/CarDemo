package com.example.hokkung.cardemo.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.adapter.CategoryAdapter
import com.example.hokkung.cardemo.adapter.TypeAdapter
import com.example.hokkung.cardemo.model.Category
import com.example.hokkung.cardemo.model.Type
import com.example.hokkung.cardemo.utils.BaseActivity
import com.example.hokkung.cardemo.utils.GoogleMapCallback
import com.example.hokkung.cardemo.utils.TouchMapFragment
import com.example.hokkung.cardemo.viewmodel.AddShopViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_add_car.*

class AddShopActivity : BaseActivity(), CategoryAdapter.OnCategoryCheckListener, TypeAdapter.OnTypeClickListener {

    val tag by lazy { this::class.java.name }
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var typeAdapter: TypeAdapter
    private lateinit var vm: AddShopViewModel
    private val googleMap by lazy { GoogleMapCallback(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        initView()
        initRecyclerView()
        initViewModel()
        initMap()
        saveCarShop()
    }

    override fun onClickCheckbox(item: Category) {
        vm.updateCategories(item)
    }

    override fun onItemClick(item: Type) {
        vm.updateTypeCar(item)
        typeAdapter.notifyDataSetChanged()
    }

    private fun initView() {
        title = getString(R.string.add_my_shop)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun saveCarShop() {
        saveCarShop.setOnClickListener { _ ->
            val name = nameshop.text.toString().trim()
            if (name.isNotEmpty()) {
                googleMap.getLocationToSave()?.let {
                    vm.createCarShop(name, it)
                }
            } else {
                scrollView.smoothScrollTo(nameshop.left, nameshop.top)
                nameshop.error = getString(R.string.fill_in_data)
                nameshop.requestFocus()
            }
        }
    }

    private fun initMap() {
        val map: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as TouchMapFragment
        map.getMapAsync(googleMap)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as TouchMapFragment
        mapFragment.setListener(object: TouchMapFragment.OnTouchListener{
            override fun onTouch() {
                scrollView.requestDisallowInterceptTouchEvent(true)
            }
        })

        googleMap.getCurrentLocation()
    }


    private fun initViewModel() {
        vm = ViewModelProviders.of(this).get(AddShopViewModel::class.java)

        vm.getAllTypes(init = true).observe(this, Observer {
            typeAdapter.submitList(it)
        })

        vm.getAllCategories().observe(this, Observer {
            categoryAdapter.submitList(it)
        })
    }


    private fun initRecyclerView() {
        typeRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        typeAdapter = TypeAdapter(this)
        typeRecyclerView.adapter = typeAdapter


        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        categoryAdapter = CategoryAdapter(this)
        recyclerView.adapter = categoryAdapter
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
