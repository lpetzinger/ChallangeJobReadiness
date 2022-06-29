package com.example.challangereadness.listener

import android.content.Context

interface ProductListener {
    fun onCLick(image:String?, title: String?, price: String?)
}