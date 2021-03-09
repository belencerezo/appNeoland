package com.example.appneoland.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appneoland.Entities.Student
import com.example.appneoland.databinding.ItemstudentBinding

class StudentsAdapter() : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>(){

    var listStudents = listOf<Student>()

    class StudentViewHolder(val itemBinding: ItemstudentBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = ItemstudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return StudentViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = listStudents[position]
        holder.itemBinding.fullName.text = student.name
        holder.itemBinding.email.text = student.email
        //holder.itemBinding.photo.setImageResource(student.photoId)

    }

    fun updateData(listStudents: List<Student>){
        this.listStudents = listStudents
        notifyDataSetChanged()
    }

}