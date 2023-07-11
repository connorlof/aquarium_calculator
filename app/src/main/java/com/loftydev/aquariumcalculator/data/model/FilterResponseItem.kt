package com.loftydev.aquariumcalculator.data.model


import com.google.gson.annotations.SerializedName

data class FilterResponseItem(
    @SerializedName("Associates Link")
    val associatesLink: String,
    @SerializedName("Image Link")
    val imageLink: String,
    @SerializedName("Product Name")
    val productName: String,
    @SerializedName("Quantity")
    val quantity: String,
    @SerializedName("Rated Gallons")
    val ratedGallons: Int,
    @SerializedName("Type")
    val type: String
)