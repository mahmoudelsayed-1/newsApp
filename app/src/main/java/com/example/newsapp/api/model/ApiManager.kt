package com.example.newsapp.api.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object {
        private var retrofit : Retrofit?=null
        private fun getInstance():Retrofit{
            if (retrofit ==null){

                // create object from Retrofit with singliton
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            //
            return getInstance().create(WebServices::class.java)
        }
    }


}