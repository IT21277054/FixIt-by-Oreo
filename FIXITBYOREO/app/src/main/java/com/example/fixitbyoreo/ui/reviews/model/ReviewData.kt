package com.example.fixit.model

data class ReviewData(
    val name : String? = null,
    val email : String? = null,
    val reviewTitle: String? =null,
    val reviewDescription: String? =null,
    var starRating: Float = 0f
){

}

