package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {

    lateinit var viewModel: CreateNewsUserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)
        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        createUserObservable()
        if (user_id != null) {
            loadUserData(user_id)
        }
        createButton.setOnClickListener {
            createUser(user_id.toString())
        }
    }

    private fun loadUserData(user_id: String?) {
        viewModel.getCreateNewUserObservable().observe(this, Observer<UserResponse?> {
            if (it != null) {
                editTextName.setText(it.data?.name)
                editTextName.setText(it.data?.email)
                createButton.setText("Update")
                deleteButton.visibility = GONE
            }
        })
        viewModel.getUserData(user_id.toString())
    }

    private fun createUser(user_id: String) {
        val user =
            User("", editTextName.text.toString(), editTextEmail.text.toString(), "Active", "Male")

        if (user_id == null)
            viewModel.createUser(user)
        else
            viewModel.updateUser(user_id, user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateNewsUserModel::class.java)
    }

    private fun createUserObservable() {
        viewModel.getCreateNewUserObservable().observe(this, Observer<UserResponse?> {
            if (it != null) {
                editTextName.setText(it.data?.name)
                editTextName.setText(it.data?.email)
                createButton.setText("Update")
                deleteButton.visibility = View.VISIBLE
            }
        })
    }
}