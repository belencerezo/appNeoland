package com.example.appneoland.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appneoland.databinding.ActivityNewuserBinding
import com.example.appneoland.viewModel.NewUserViewModel

class NewUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewuserBinding

    private lateinit var model: NewUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(NewUserViewModel::class.java)

        binding.buttonGuardar.setOnClickListener {
            model.insertUser(binding.nameEdit.text.toString(), binding.lastnameEdit.text.toString(), binding.emailEdit.text.toString(), 0)
            finish()
        }

        binding.emailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    binding.buttonGuardar.isEnabled = p0.contains("@") && p0.contains(".")
                }
            }
        } )
    }
}