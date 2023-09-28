package com.example.apiconsumer

import retrofit2.Call
import retrofit2.http.GET


interface PokemonAPIService {
    @get:GET("pokemon/?limit=50")
    val pokemons: Call<*>?
}