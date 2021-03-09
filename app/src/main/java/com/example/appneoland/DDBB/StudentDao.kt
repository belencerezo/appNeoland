package com.example.appneoland.DDBB

import androidx.room.*
import com.example.appneoland.Entities.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    ///ALLIVE

    @Insert
    fun insert(Student: Student)

    @Update
    fun update(Student: Student)

    @Insert
    fun insertAll(Student: List<Student>)

    @Delete
    fun delete(Student: Student)

}