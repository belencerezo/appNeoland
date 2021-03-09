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
import kotlinx.coroutines.async

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {

    companion object{
        const val USER_EMAIL = "USER_EMAIL"
    }

    fun cargarPreferencias() : String? {
        val sharedPref = getApplication<App>().getSharedPreferences("preferences", Context.MODE_PRIVATE )
        return sharedPref.getString(MainActivityViewModel.USER_EMAIL, "")
    }

    fun guardarPreferencias(string : String) {
      val sharedPref = getApplication<App>().getSharedPreferences("preferences", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(MainActivityViewModel.USER_EMAIL, string)
            commit()
        }
    }


   suspend fun isUserDB (email: String) : Boolean {

       var found = false
       viewModelScope.async(Dispatchers.IO) {
           val listStudentsInDB = Db.getDatabase(getApplication()).studentDao().getAll()
           var index = 0
           do {
               if (listStudentsInDB[index].email.contentEquals(email)) {
                   found = true
               }
               index += 1
           } while (!found && index < listStudentsInDB.size )
       } .await()
       return found

   }
}

