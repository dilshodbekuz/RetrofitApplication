package com.example.retrofitapplication


import retrofit2.Call
import retrofit2.http.*

interface RetroServise {
    //https://gorest.co.in/public-api/users
    @GET("users")
    @Headers("Accept:application/json","Content-type:application/json")
    fun getUserList(): Call<UserList>

    //https://gorest.co.in/public-api/users?name=a
    @GET("users")
    @Headers("Accept:application/json","Content-type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>

    //https://gorest.co.in/public-api/users/121
    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<UserResponse>

    @POST("users")
    @Headers("Accept:application/json","Content-type:application/json",
    "Authorization:Bearer 3e24ecda2a6b44561e3fcac1e41d7e416b7d2456cc91725c66e95329ba06c794")
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-type:application/json",
        "Authorization:Bearer 3e24ecda2a6b44561e3fcac1e41d7e416b7d2456cc91725c66e95329ba06c794")
    fun updateUser(@Path("user_id") user_id: String,@Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json","Content-type:application/json",
        "Authorization:Bearer 3e24ecda2a6b44561e3fcac1e41d7e416b7d2456cc91725c66e95329ba06c794")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}