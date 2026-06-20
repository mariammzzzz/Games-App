package com.mjapa21.gamesapp.data

import com.mjapa21.gamesapp.data.mode.GamesListItem
import retrofit2.http.GET

interface GamesApi {

    @GET("games")
    suspend fun getGamesList(): List<GamesListItem>
}