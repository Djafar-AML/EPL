package com.example.epl

data class SoccerTile(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val descriptionLong: String = "",
    val buttonText: String = "",
    val headerImageResourceId: Int = 0,
    val headerImageResourceUrl: String? = null,
): java.io.Serializable