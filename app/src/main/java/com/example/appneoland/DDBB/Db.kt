package com.example.appneoland.DDBB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appneoland.Entities.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database (entities = [Student::class], version = 1)

abstract class Db : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {

        @Volatile
        var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this){
                INSTANCE?.let {
                    return INSTANCE as Db
                }

                val room = Room.databaseBuilder(context.applicationContext, Db::class.java, "database-db")
                room.addCallback(getCallback())
                INSTANCE = room.build()
                return INSTANCE as Db
            }
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val students: List<Student> = listOf(
                                Student("Bel", "C", "bel@gmail.com", 1),
                                Student("Sergi", "E", "sergi@gmail.com", 2),
                                Student("Adri", "A", "adri@gmail.com", 3),
                                Student("Elena", "B", "elena@gmail.com", 4),
                                Student("Carlos", "D", "carlos@gmail.com", 5)
                        )

                        INSTANCE?.studentDao()?.insertAll(students)

                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase){

                }
            }
        }
    }
}
