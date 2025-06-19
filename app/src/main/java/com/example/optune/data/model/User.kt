package com.example.optune.data.model


data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val signUpMethod: String? = null,
    val skills: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val education:String? = null,
    val cv: String? = null,

    )


