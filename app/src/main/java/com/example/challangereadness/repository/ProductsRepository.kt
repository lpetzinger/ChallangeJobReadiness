package com.example.challangereadness.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.challangereadness.listener.ErrorListener
import com.example.challangereadness.model.HighLight.HighLightsModel
import com.example.challangereadness.model.Product.ProductModel
import com.example.challangereadness.service.ProductService
import com.example.challangereadness.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProductsRepository private constructor(val context: Context, val errorListener: ErrorListener) {

    companion object {
        private lateinit var repository: ProductsRepository

        fun getInstance(context: Context, errorListener: ErrorListener): ProductsRepository {
            if (!Companion::repository.isInitialized) {
                repository = ProductsRepository(context, errorListener)
                return repository
            }
            return repository
        }
    }


    private fun getProducts(list: List<String>, _products: MutableLiveData<List<ProductModel>>) {
        val queryString = StringBuffer()
        list.forEach {
            queryString.append("$it,")
        }
        val newQueryString: String = queryString.toString()
        val service = RetrofitClient.create(ProductService::class.java)
        val call: Call<List<ProductModel>> = service.getProducts(newQueryString)


        try {
            call.enqueue(object : Callback<List<ProductModel>> {
                override fun onResponse(
                    call: Call<List<ProductModel>>,
                    response: Response<List<ProductModel>>
                ) {
                    _products.value = response.body()!!.filter {
                        it.body?.pictures!!.isNotEmpty()
                    }


                }

                override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                    Log.d("xablau", "$t")
                }

            })
        } catch (e: Exception) {
            Log.d("xablau", "$e")
        }


    }


    fun createList(
        response: Response<HighLightsModel>,
        _products: MutableLiveData<List<ProductModel>>
    ) {
        val list = mutableListOf<String>()
        response.body()?.content?.forEach {
            list.add(it.id)

        }
        getProducts(list, _products)
    }


    fun setCategory(_category: String, _products: MutableLiveData<List<ProductModel>>) {
        val service = RetrofitClient.create(ProductService::class.java)
        val call: Call<HighLightsModel> = service.getIdProducts(_category)
        try {
            call.enqueue(object : Callback<HighLightsModel> {
                override fun onResponse(
                    call: Call<HighLightsModel>,
                    response: Response<HighLightsModel>
                ) {
                    if (response.raw().code() != 200) {
                        setCategory("MLB437616", _products)
                            errorListener.isInvalidCategory()
                    } else {
                        createList(response, _products)

                    }
                }

                override fun onFailure(call: Call<HighLightsModel>, t: Throwable) {
                    Log.d("xablau", "$t")
                }

            })
        } catch (e: Exception) {
            Log.d("Xablau 2", "$e")
        }


    }

}