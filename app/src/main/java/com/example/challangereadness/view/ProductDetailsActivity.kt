package com.example.challangereadness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModelProvider
import com.example.challangereadness.R
import com.example.challangereadness.databinding.ActivityProductDetailsBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
import com.example.challangereadness.viewModel.MainViewModel
import com.example.challangereadness.viewModel.ProductDetailsViewModel
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        super.onCreate(savedInstanceState)
        getProductInfo()
        validFavorite()
        setListeners()
        setContentView(binding.root)

    }

    private fun setListeners() {
        binding.imageButtonBack.setOnClickListener {
            finish()
        }

        binding.buttonFavorite.setOnClickListener {
            val productState = StatePreferences(this)
            val favorites = StatePreferences(this).getFavoriteState(ConstantKeys.FAVORITES)
            val id: String = productState.getProductState(ConstantKeys.ID)
            val isFavorite = favorites.contains(id)

            if (!isFavorite) {
                StatePreferences(this).setFavoriteState(ConstantKeys.FAVORITES, "$favorites,$id")
                Toast.makeText(this, ConstantKeys.ADD_FAVORITES, Toast.LENGTH_SHORT).show()
            } else {
                StatePreferences(this).setFavoriteState(
                    ConstantKeys.FAVORITES,
                    favorites.replace(id, "")
                )
                Toast.makeText(this, ConstantKeys.REMOVE_FAVORITES, Toast.LENGTH_SHORT).show()

            }
            validFavorite()


        }
    }

    private fun validFavorite() {
        val productState = StatePreferences(this)
        val favorites = StatePreferences(this).getFavoriteState(ConstantKeys.FAVORITES)
        val id: String = productState.getProductState(ConstantKeys.ID)
        val isFavorite = favorites.contains(id)
        val source = if (isFavorite) R.drawable.ic_is_favorite else R.drawable.ic_favorite
        Picasso.get().load(source).error(R.drawable.placeholder).into(binding.buttonFavorite)

    }

    private fun getProductInfo() {
        val productState = StatePreferences(this)
        val id: String = productState.getProductState(ConstantKeys.ID)

        viewModel.getProductInfo(binding, id)
    }
}