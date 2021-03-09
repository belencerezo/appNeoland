package com.example.appneoland.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.appneoland.databinding.ActivityMainBinding
import com.example.appneoland.viewModel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var model: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Quiero que si se pulsa el botón "iniciar": a) si el checkedbox está activo, guarde el texto que hay en el editext.
        //b) si el checkbox no está activo, borro los que hubiera guardado antes

        binding.bLogin.setOnClickListener {

            if (binding.remember.isEnabled && binding.remember.isChecked){
                Toast.makeText(this, "El check box esta seleccionado", Toast.LENGTH_LONG).show()
                model.guardarPreferencias(binding.editText.text.toString())

            } else {
                Toast.makeText(this, "El check box no esta seleccionado", Toast.LENGTH_LONG).show()
                model.guardarPreferencias("")
            }

            lifecycleScope.launch {
               if (model.isUserDB(binding.editText.text.toString())) {
                   startStudentActivity()

                } else {
                    Toast.makeText(this@MainActivity, "QUE PASA", Toast.LENGTH_LONG).show()

                }
            }
        }

        // Quiero que el checkbox recordar usuario este deshabilitado hasta
        // que en el texto no haya una "@" y un "."

        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    binding.remember.isEnabled = p0.contains("@") && p0.contains(".")
                }
            }
        } )

        model.cargarPreferencias()?.let {
            binding.editText.setText(it)
        }
    }

    private fun startStudentActivity(){
        val intent = Intent(this, StudentsActivity::class.java)
        startActivity(intent)
    }



}