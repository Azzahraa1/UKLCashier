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
import com.example.uklcashier.adapter.MenuAdapter
import com.example.uklcashier.data.AppDatabase2
import com.example.uklcashier.data.entity.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<Menu>()
    private var list2 = mutableListOf<Menu>()


    private lateinit var database : AppDatabase2
    private lateinit var adapter : MenuAdapter
    private lateinit var adapter2 : MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerview2)
        recyclerView2 = findViewById(R.id.recyclerview3)
        fab = findViewById(R.id.fab2)

        database = AppDatabase2.getInstance(applicationContext)
        adapter = MenuAdapter(list)
        adapter2 = MenuAdapter(list2)
        adapter.setDialog(object : MenuAdapter.Dialog2{
                override fun onClick(position: Int) {
                //membuat dialog view 2
                val dialog2 = AlertDialog.Builder(this@HomeActivity)
                dialog2.setTitle(list[position].namaMenu)
                dialog2.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog2,which ->
                    if(which == 0){
                        //coding ubah
                        val intent2 = Intent(this@HomeActivity, MenuActivity::class.java)
                        intent2.putExtra("id", list[position].mid)
                        startActivity(intent2)
                        //coding hapus
                    }else if (which == 1){
                        database.MenuDao().delete(list[position])
                        getData()
                        //coding batal
                    }else{
                        dialog2.dismiss()
                    }
                })
                //menampilkan dilaog
                val dialogView2 = dialog2.create()
                dialogView2.show()
            }
        })

        adapter2.setDialog(object : MenuAdapter.Dialog2{
            override fun onClick(position: Int) {
                //membuat dialog view 2
                val dialog2 = AlertDialog.Builder(this@HomeActivity)
                dialog2.setTitle(list2[position].namaMenu)
                dialog2.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog2,which ->
                    if(which == 0){
                        //coding ubah
                        val intent2 = Intent(this@HomeActivity, MenuActivity::class.java)
                        intent2.putExtra("id", list2[position].mid)
                        startActivity(intent2)
                        //coding hapus
                    }else if (which == 1){
                        database.MenuDao().delete(list2[position])
                        getData()
                        //coding batal
                    }else{
                        dialog2.dismiss()
                    }
                })
                //menampilkan dilaog
                val dialogView2 = dialog2.create()
                dialogView2.show()
            }
        })

//


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView2.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this,MenuActivity::class.java))


        }


    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list2.clear()
        list.addAll(database.MenuDao().getMenuFilterJenis("Makanan"))
        list2.addAll(database.MenuDao().getMenuFilterJenis("Minuman"))
        adapter.notifyDataSetChanged()
        adapter2.notifyDataSetChanged()
    }
}