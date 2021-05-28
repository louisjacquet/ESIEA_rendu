package com.example.esieatp1.presentation.list

import com.example.esieatp1.presentation.PokeApplication.Companion.context
import com.example.esieatp1.presentation.api.PokeApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class Singletons {
    companion object{

        var cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

        val okhttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()
        val pokeApi: PokeApi = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") //URL : https://superheroapi.com/api/834483354172660
                .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()
            .create(PokeApi::class.java)


    }
}

