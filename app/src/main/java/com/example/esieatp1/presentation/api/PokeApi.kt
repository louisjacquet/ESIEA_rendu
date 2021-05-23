package com.example.esieatp1.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeApi {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonResponse>
    //@Query("limit") limit : sort : String
}