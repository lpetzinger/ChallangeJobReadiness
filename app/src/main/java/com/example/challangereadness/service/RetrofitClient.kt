package com.example.challangereadness.service


import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    class OAuthInterceptor(
        private val tokenType: String,
        private val acceessToken: String
    ) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request =
                chain.request().newBuilder().addHeader("Authorization", "$tokenType $acceessToken")
                    .build()

            return chain.proceed(request)
        }
    }

    companion object {
        private val client = OkHttpClient.Builder()
            .addInterceptor(
                OAuthInterceptor(
                    "Bearer",
                    "APP_USR-3967523105214896-070113-82a1fb2cf8746c1c83772979dfa6df41-139547814"
                )
            )
            .build()

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        fun <T> create(service: Class<T>): T {
            return retrofit.create(service)
        }


    }

}