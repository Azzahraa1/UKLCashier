package com.example.uklcashier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklcashier.data.AppDatabase
import com.example.uklcashier.data.AppDatabase3
import com.example.uklcashier.data.entity.Meja

class MejaActivity : AppCompatActivity() {

    private lateinit var nomeja : EditText
    private lateinit var btnSave : Button

    private lateinit var database: AppDatabase3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja)

        nomeja = findViewById(R.id.nomeja)
        btnSave = findViewById(R.id.btnSavee)

        database = AppDatabase3.getInstance(applicationContext)
        val intent  = intent.extras
        if(intent != null){
            val id = intent.getInt("id",0)
            var meja = database.MejaDao().get(id)

            nomeja.setText(meja.noMeja)
        }
        btnSave.setOnClickListener {
            if(nomeja.text.isNotEmpty()){
                if(intent != null){
                    //coding ubah data

                    database.MejaDao().update(
                        Meja(
                            intent.getInt("id",0),
                            nomeja.text.toString()
                        )
                    )
                }else{
                    //coding tambah data
                    database.MejaDao().insertAll(
                        Meja(
                            null,
                            nomeja.text.toString()
                        )
                    )
                }
            finish()
        } else{
            Toast.makeText(applicationContext,"Silahkan isi no meja", Toast.LENGTH_SHORT)
                .show()
        }}
    }
}