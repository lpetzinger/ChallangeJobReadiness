package com.example.challangereadness.viewModel

import android.app.Application
import android.graphics.Paint
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.challangereadness.R
import com.example.challangereadness.databinding.ActivityProductDetailsBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.model.Product.ProductModel
import com.example.challangereadness.service.ProductService
import com.example.challangereadness.service.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel(application: Application) : AndroidViewModel(application) {


    fun getProductInfo(binding: ActivityProductDetailsBinding, id: String) {
        val service = RetrofitClient.create(ProductService::class.java)
        val call: Call<List<ProductModel>> = service.getProducts(id)


        try {
            call.enqueue(object : Callback<List<ProductModel>> {
                override fun onResponse(
                    call: Call<List<ProductModel>>,
                    response: Response<List<ProductModel>>
                ) {
                    val data = response.body()!![0].body

                    binding.textTitle.text = data?.title

                    binding.textPrice.text = ConstantKeys.formatToCurrency(data!!.price, "BRL", 2)



                    val validOldPrice = if(data.originalPrice != 0F) data.originalPrice else data.price
                    binding.textOldPrice.text =
                        ConstantKeys.formatToCurrency(validOldPrice, "BRL", 2)
                    binding.textOldPrice.paintFlags = binding.textOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    binding.textSubtitle.text =
                        if (data.condition == "new") "Novo - ${data.soldQuantity} vendidos" else "Usado"

                    binding.textInstallment.text = binding.textInstallment.text.toString().replace(
                        "R$00",
                        ConstantKeys.formatToCurrency(data.price / 18, "BRL", 2)
                    )

                    Picasso.get().load(data.pictures[0].secure_url)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(binding.imageProduct)


                }

                override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                    Log.d("xablau", "$t")
                }

            })
        } catch (e: Exception) {
            Log.d("xablau", "$e")
        }

    }

}