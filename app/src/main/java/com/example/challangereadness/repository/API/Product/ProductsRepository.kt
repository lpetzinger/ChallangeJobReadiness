package com.example.challangereadness.repository.API.Product

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.challangereadness.repository.API.HighLight.HighLights
import com.example.challangereadness.repository.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProductsRepository private constructor(context: Context) {

    companion object {
        private lateinit var repository: ProductsRepository

        fun getInstance(context: Context): ProductsRepository {
            if (!Companion::repository.isInitialized) {
                repository = ProductsRepository(context)
                return repository
            }
            return repository
        }
    }


    private fun getProducts(list: List<String>, _products: MutableLiveData<List<ProductEntity>>) {
        val queryString = StringBuffer()
        list.forEach {
            queryString.append("$it,")
        }
        val newQueryString: String = queryString.toString()
        val service = RetrofitClient.create(ProductService::class.java)
        val call: Call<List<ProductEntity>> = service.getProducts(newQueryString)


        try {
            call.enqueue(object : Callback<List<ProductEntity>> {
                override fun onResponse(
                    call: Call<List<ProductEntity>>,
                    response: Response<List<ProductEntity>>
                ) {
                    _products.value = response.body()


                }

                override fun onFailure(call: Call<List<ProductEntity>>, t: Throwable) {
                    Log.d("xablau", "$t")
                }

            })
        } catch (e: Exception) {
            Log.d("xablau", "$e")
        }


    }


    fun createList(
        response: Response<HighLights>,
        _products: MutableLiveData<List<ProductEntity>>
    ) {
        val list = mutableListOf<String>()
        response.body()?.content?.forEach {
            list.add(it.id)

        }
        getProducts(list, _products)
    }


    fun setCategory(_category: String, _products: MutableLiveData<List<ProductEntity>>) {
        val service = RetrofitClient.create(ProductService::class.java)
        val call: Call<HighLights> = service.getIdProducts(_category)
        try {
            call.enqueue(object : Callback<HighLights> {
                override fun onResponse(call: Call<HighLights>, response: Response<HighLights>) {
                    createList(response, _products)

                }

                override fun onFailure(call: Call<HighLights>, t: Throwable) {
                    Log.d("xablau", "$t")
                }

            })
        } catch (e: Exception) {
            Log.d("Xablau 2", "$e")
        }


    }

}