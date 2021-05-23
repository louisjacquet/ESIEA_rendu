package com.example.esieatp1.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esieatp1.R
import com.example.esieatp1.presentation.PokemonAdapter
import com.example.esieatp1.presentation.api.PokeApi
import com.example.esieatp1.presentation.api.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragement : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private val adapter = PokemonAdapter(listOf(), :: onClickedpokemon)



    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.pokemon_recyclerview)
        recyclerView.apply {
            layoutManager = this@PokemonListFragement.layoutManager
            adapter = this@PokemonListFragement.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)
        pokeApi.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            //object : Callback<PokemonResponse>
            override fun onFailure(
                call: Call<PokemonResponse>,
                t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    val PokemonResponse = response.body() !!
                    adapter.updateList(PokemonResponse.results)

                }
            }

        })


        /*val pokeList = arrayListOf<Pokemon>().apply {
            add(Pokemon("CAIRN TERRIER"))
            add(Pokemon("COCKER SPANIEL ANGLAIS"))
            add(Pokemon("SETTER GORDON"))
            add(Pokemon("AUSTRALIAN TERRIER"))
            add(Pokemon("CHIEN DE BERGER BELGE"))
            add(Pokemon("GRIFFON NIVERNAIS"))
            add(Pokemon("ARIEGEOIS"))
            add(Pokemon("GASCON SAINTONGEOIS"))
            add(Pokemon("GRAND BLEU DE GASCOGNE"))
            add(Pokemon("POITEVIN"))
            add(Pokemon("CHIEN D'ARTOIS"))
            add(Pokemon("PORCELAINE"))
        }

        adapter.updateList(pokeList)

         */
    }
    private fun onClickedpokemon(pokemon: Pokemon) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragement)
    }

}