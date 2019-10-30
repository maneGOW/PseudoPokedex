package com.dev.manegow.PsuedoPokedex

import io.reactivex.Observable
import retrofit2.http.GET

interface GetData {
    @GET("pokemon/")
    fun getPokedexData() : Observable<PokeDexData>
}