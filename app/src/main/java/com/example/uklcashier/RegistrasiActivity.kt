package com.example.uklcashier

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.uklcashier.adapter.UserAdapter
import com.example.uklcashier.data.AppDatabase
import com.example.uklcashier.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegistrasiActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<User>()

    private lateinit var database : AppDatabase
    private lateinit var adapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        recyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{

            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@RegistrasiActivity)
                dialog.setTitle(list[position].namaUser)
                dialog.setItems(R.array.items_option,DialogInterface.OnClickListener{dialog, which ->
                    if (which == 0){
                        //coding ubah
                        val intent = Intent(this@RegistrasiActivity, UserActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                        //coding hapus
                    } else if (which == 1){
                        database.UserDao().delete(list[position])
                        getData()
                        //coding batal
                    } else{
                        dialog.dismiss()
                    }
                })
                //menampilkan dialog
                val dialogView = dialog.create()
                dialogView.show()

            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.UserDao().getAll())
        adapter.notifyDataSetChanged()
    }
}