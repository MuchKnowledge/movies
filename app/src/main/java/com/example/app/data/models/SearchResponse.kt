package com.example.app.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search") val movies: List<MovieResponse>
)