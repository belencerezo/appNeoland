package com.example.appneoland.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appneoland.Entities.Student
import com.example.appneoland.DDBB.Db
import kotlinx.coroutines.*

class StudentsViewModel(application: Application) : AndroidViewModel(application) {

    val listStudents: MutableLiveData<List<Student>> = MutableLiveData()

    fun getAllStudents(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = Db.getDatabase(getApplication()).studentDao().getAll()
            withContext(Dispatchers.Main) {
                listStudents.value = list
            }
        }
    }
}