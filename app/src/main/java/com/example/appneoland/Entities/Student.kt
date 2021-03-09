package com.example.appneoland.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


//@Parcelable

@Entity
class Student (val name: String, val lastname: String, val email: String, val photoId: Int) {
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0

        var fullName: String = "$name $lastname "
}