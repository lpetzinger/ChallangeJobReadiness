package com.example.challangereadness.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challangereadness.model.ProductModel
import com.example.challangereadness.repository.ProductsRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _products = MutableLiveData<List<ProductModel>>()
    private val repository = ProductsRepository.getInstance(application)
    val products: LiveData<List<ProductModel>> = _products

    fun getAll(){
        val list = repository.getAll()
        _products.value = list
    }
}
