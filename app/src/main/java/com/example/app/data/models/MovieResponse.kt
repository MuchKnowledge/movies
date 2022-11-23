package com.example.app.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("imdbID") val id: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("Year") val year: String?,
    @SerializedName("Type") val type: String?,
    @SerializedName("Plot") val plot: String?,
    @SerializedName("imdbRating") val imdbRating: String?,
    @SerializedName("Language") val language: String?,
    @SerializedName("Writer") val writer: String?,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("Poster") val poster: String?,
)