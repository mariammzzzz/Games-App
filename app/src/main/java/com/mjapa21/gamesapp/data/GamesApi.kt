package com.mjapa21.gamesapp.data

import com.mjapa21.gamesapp.data.model.GameDetails
import com.mjapa21.gamesapp.data.model.GamesListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {

    @GET("games")
    suspend fun getGamesList(): List<GamesListItem>

    @GET("game")
    suspend fun getGameDetails(
        @Query("id") gameId: Int
    ): GameDetails

}