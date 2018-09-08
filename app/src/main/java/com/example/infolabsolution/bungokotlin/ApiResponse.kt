package com.example.infolabsolution.bungokotlin

data class  ApiResponse(
        val events: List<MatchDetail>,
        val teams: List<MatchDetail>
)

