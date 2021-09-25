package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Rabbit(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String
)

