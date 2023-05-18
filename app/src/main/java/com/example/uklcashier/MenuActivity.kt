package com.example.uklcashier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.example.uklcashier.data.AppDatabase2
import com.example.uklcashier.data.entity.Menu

class MenuActivity : AppCompatActivity() {

    private lateinit var namamenu : EditText
    private lateinit var desk : EditText
    private lateinit var harga : EditText
    private lateinit var btnSave : Button
    private lateinit var jenismenu : Spinner

    private lateinit var database : AppDatabase2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        init()
        setDataSpinner()
        val intent = intent.extras
        if(intent != null){
            val id = intent.getInt("id", 0)
            var menu = database.MenuDao().get(id)

            namamenu.setText(menu.namaMenu)
            desk.setText(menu.deskripsi)
            harga.setText(menu.harga)}

        btnSave.setOnClickListener{
            if(namamenu.text.toString().isNotEmpty() && desk.text.toString().isNotEmpty() && harga.text.toString().isNotEmpty()) {
                if (intent != null) {
                    database.MenuDao().update(
                        Menu(
                            intent.getInt("id", 0),
                            namamenu.text.toString(),
                            desk.text.toString(),
                            harga.text.toString(),
                            jenismenu.selectedItem.toString()
                        )
                    )
                } else{
                    database.MenuDao().insertAll(
                        Menu(
                            null,
                            namamenu.text.toString(),
                            desk.text.toString(),
                            harga.text.toString(),
                            jenismenu.selectedItem.toString()
                        )
                    )
                }
                finish()
            }else {
                Toast.makeText(applicationContext, "Silahka isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun init(){
        namamenu = findViewById(R.id.namamenu)
        desk = findViewById(R.id.desk)
        harga = findViewById(R.id.harga)
        jenismenu = findViewById(R.id.spinmenu)
        btnSave = findViewById(R.id.btnSave2)


        database = AppDatabase2.getInstance(applicationContext)
    }

    private fun setDataSpinner(){
        val adapter = ArrayAdapter.createFromResource(applicationContext, R.array.tipe, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jenismenu.adapter = adapter
    }
}








