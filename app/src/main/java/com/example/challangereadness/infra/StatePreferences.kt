package com.example.challangereadness.infra

import android.content.Context
import android.content.SharedPreferences

const val ERROR_MESSAGE = "Erro ao carregar informação"
const val STATE = ConstantKeys.PRODUCT_INFO

class StatePreferences(context: Context) {
    private val productInfo: SharedPreferences =
        context.getSharedPreferences(STATE, Context.MODE_PRIVATE)

    private val favorites: SharedPreferences = context.getSharedPreferences(ConstantKeys.FAVORITES, Context.MODE_PRIVATE)

    fun setProductState(key: String, value: String?) = productInfo.edit().putString(key, value).apply()
    fun getProductState(key: String) = productInfo.getString(key, ERROR_MESSAGE) ?: ERROR_MESSAGE
    fun setFavoriteState(key: String, value: String?) = favorites.edit().putString(key, value).apply()
    fun getFavoriteState(key: String) = favorites.getString(key, ERROR_MESSAGE) ?: ERROR_MESSAGE




}