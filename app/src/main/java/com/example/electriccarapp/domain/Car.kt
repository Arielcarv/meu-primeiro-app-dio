package com.example.electriccarapp.domain

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id")
    val id: Int,
    @SerializedName("preco")
    val price: String,
    @SerializedName("bateria")
    val battery: String,
    @SerializedName("potencia")
    val power: String,
    @SerializedName("recarga")
    val charge: String,
    @SerializedName("urlPhoto")
    val photoUrl: String,
    var isFavorite: Boolean
)