package com.example.appneoland.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appneoland.DDBB.App
import com.example.appneoland.DDBB.Db
import com.example.appneoland.Entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserViewModel(application: Application) : AndroidViewModel(application) {

    fun insertUser(name: String, lastname: String, email: String, photo: Int){

        var student = Student(name, lastname, email, photo)
        viewModelScope.launch(Dispatchers.IO) {
            Db.getDatabase(getApplication()).studentDao().insert(student)
        }
    }

}