package com.example.challangereadness.listener

import android.content.Context
import com.example.challangereadness.databinding.CardProductBinding

interface ProductListener {
    fun onCLick(id: String)
    fun onFavorite(holderBinding: CardProductBinding, id: String)
}