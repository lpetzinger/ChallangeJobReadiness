package com.example.challangereadness.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangereadness.R
import com.example.challangereadness.adapter.ProductsAdapter
import com.example.challangereadness.databinding.ActivityMainBinding
import com.example.challangereadness.databinding.CardProductBinding
import com.example.challangereadness.infra.ConstantKeys
import com.example.challangereadness.infra.StatePreferences
import com.example.challangereadness.listener.ErrorListener
import com.example.challangereadness.listener.ProductListener
import com.example.challangereadness.viewModel.MainViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), ErrorListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter = ProductsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainViewModel(application, this)
        super.onCreate(savedInstanceState)
        getCategory()
        startAdapter()
        observe()
        setContentView(binding.root)
        setListeners()

    }

    override fun onResume() {
        super.onResume()
        startAdapter()
    }

    private fun getCategory() {
        viewModel.getCategory(binding)

    }

    private fun setListeners() {
        binding.imageSearch.setOnClickListener {
            getCategory()
        }
        val inputSearch = binding.editTextInputSearch
        inputSearch.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getCategory()
                return@OnKeyListener true
            }
            false
        })

        val productsState = StatePreferences(this)
        val productListener = object : ProductListener {
            override fun onCLick(id: String) {
                productsState.setProductState("id", id)
                startActivity(Intent(this@MainActivity, ProductDetailsActivity::class.java))
            }

            override fun onFavorite(holderBinding: CardProductBinding, id: String) {
                val productState = StatePreferences(this@MainActivity)
                val favorites =
                    StatePreferences(this@MainActivity).getFavoriteState(ConstantKeys.FAVORITES)
                val isFavorite = favorites.contains(id)

                if (!isFavorite) {
                    StatePreferences(this@MainActivity).setFavoriteState(
                        ConstantKeys.FAVORITES,
                        "$favorites,$id"
                    )
                    Toast.makeText(
                        this@MainActivity,
                        ConstantKeys.ADD_FAVORITES,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    StatePreferences(this@MainActivity).setFavoriteState(
                        ConstantKeys.FAVORITES,
                        favorites.replace(id, "")
                    )


                    Toast.makeText(
                        this@MainActivity,
                        ConstantKeys.REMOVE_FAVORITES,
                        Toast.LENGTH_SHORT
                    ).show()

                }
                validFavorite(id, productState, holderBinding)
            }

        }

        adapter.attachListener(productListener)

    }

    private fun validFavorite(
        id: String,
        productState: StatePreferences,
        holderBinding: CardProductBinding
    ) {
        val favorites = StatePreferences(this).getFavoriteState(ConstantKeys.FAVORITES)
        val isFavorite = favorites.contains(id)
        val source = if (isFavorite) R.drawable.ic_is_favorite else R.drawable.ic_favorite
        Picasso.get().load(source).error(R.drawable.placeholder).into(holderBinding.buttonFavorite)

    }


    private fun observe() {
        viewModel.products.observe(this) {
            adapter.updateProducts(it)

        }
    }

    private fun startAdapter() {
        binding.recyclerProducts.layoutManager =
            GridLayoutManager(this, 2)
        binding.recyclerProducts.adapter = adapter
    }

    override fun isInvalidCategory() {
        AlertDialog.Builder(this)
            .setTitle(ConstantKeys.DIALOG_TITLE)
            .setMessage(ConstantKeys.DIALOG_MESSAGE)
            .setNeutralButton("Ok", null)
            .show()
    }
}






