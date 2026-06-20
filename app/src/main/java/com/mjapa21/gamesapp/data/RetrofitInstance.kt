package com.mjapa21.gamesapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://www.freetogame.com/api/"

    fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createGamesApi(): GamesApi {
        return createRetrofitInstance().create(GamesApi::class.java)
    }

}