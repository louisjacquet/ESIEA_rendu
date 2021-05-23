package com.example.esieatp1.presentation.api

import com.example.esieatp1.presentation.list.Pokemon

data class PokemonResponse(
    val count : Int,
    val next : String,
    val results : List <Pokemon>
)