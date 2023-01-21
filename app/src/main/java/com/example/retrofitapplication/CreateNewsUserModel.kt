package com.example.retrofitapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.internal.userAgent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewsUserModel : ViewModel() {
    var createNewUserLiveData: MutableLiveData<UserResponse?> = MutableLiveData()
    lateinit var loadUserData: MutableLiveData<UserResponse?>

    init {
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
    }

    fun getCreateNewUserObservable(): MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }

    fun getLoadUserObservable(): MutableLiveData<UserResponse?> {
        return loadUserData
    }

    fun createUser(user: User) {
        val retroInstanse = RetroInstance.getRetroInstance().create(RetroServise::class.java)
        val call = retroInstanse.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }
    fun getUserData(user_id: String) {
        val retroInstanse = RetroInstance.getRetroInstance().create(RetroServise::class.java)
        val call = retroInstanse.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }
        })
    }
    fun updateUser(user_id:String,user: User) {
        val retroInstanse = RetroInstance.getRetroInstance().create(RetroServise::class.java)
        val call = retroInstanse.updateUser(user_id,user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }
        })
    }
}