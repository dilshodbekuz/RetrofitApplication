package com.example.retrofitapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var recyclerListData: MutableLiveData<UserList> = MutableLiveData()

    fun getUserListObserverable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUserList() {
        val retroInstanse = RetroInstance.getRetroInstance().create(RetroServise::class.java)
        val call = retroInstanse.getUserList()
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
    fun searchUser(searchText:String) {

        val retroInstanse = RetroInstance.getRetroInstance().create(RetroServise::class.java)
        val call = retroInstanse.searchUsers(searchText)
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}