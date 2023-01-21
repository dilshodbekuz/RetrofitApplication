package com.example.retrofitapplication

data class UserList (val data: List<User>)
data class User(val id:String?,val name:String?,val email: String?,val status:String?,val gender:String?)
data class UserResponse(var code: Int?,var meta: String?,var data: User?)