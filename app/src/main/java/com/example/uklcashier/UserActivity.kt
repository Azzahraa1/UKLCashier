package com.example.uklcashier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklcashier.data.AppDatabase
import com.example.uklcashier.data.entity.User
import com.google.android.material.textfield.TextInputEditText

class UserActivity : AppCompatActivity() {

    private lateinit var namauser : EditText
    private lateinit var email : EditText
    private lateinit var phone : EditText
    private lateinit var password : EditText
    private lateinit var btnSave : Button

    private lateinit var database : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        namauser = findViewById(R.id.namauser)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        password = findViewById(R.id.password)
        btnSave = findViewById(R.id.btnSave)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if(intent != null){
            val id = intent.getInt("id", 0)
            var user = database.UserDao().get(id)

            namauser.setText(user.namaUser)
            email.setText(user.email)
            password.setText(user.password)
            phone.setText(user.phone)
        }

        btnSave.setOnClickListener {
            if (namauser.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty()) {
                if (intent!= null){
                    //coding ubah data
                    database.UserDao().update(
                        User(
                            intent.getInt("id", 0),
                            namauser.text.toString(),
                            email.text.toString(),
                            password.text.toString(),
                            phone.text.toString()
                        )

                    )
                }else {
                    //coding tambah data
                    database.UserDao().insertAll(
                        User(
                            null,
                            namauser.text.toString(),
                            email.text.toString(),
                            password.text.toString(),
                            phone.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahka isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}