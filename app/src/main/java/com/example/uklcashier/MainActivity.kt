package com.example.uklcashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import java.time.temporal.TemporalAmount

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegist = findViewById<Button>(R.id.btnRegist)
        val btnMeja = findViewById<Button>(R.id.btnMeja)

        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnRegist.setOnClickListener {
            val moveIntent = Intent(this,RegistrasiActivity::class.java)
            startActivity(moveIntent)
        }
        btnMeja.setOnClickListener {
            val moreIntent = Intent(this, TmpilMejaActivity::class.java)
            startActivity(moreIntent)
        }


    }
}