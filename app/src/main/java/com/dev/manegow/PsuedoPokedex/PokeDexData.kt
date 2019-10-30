package com.dev.manegow.PsuedoPokedex

import com.google.gson.annotations.SerializedName


data class PokeDexData(
    @SerializedName("count")
    val Count: String,
    @SerializedName("next")
    val Next: String,
    @SerializedName("previous")
    val Previous: String,
    @SerializedName("results")
    val Results: List<Results>)

data class Results(
    @SerializedName("name")
    val Name: String,
    @SerializedName("url")
    val URL: String
)
