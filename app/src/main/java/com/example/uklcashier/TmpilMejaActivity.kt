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
import com.example.uklcashier.adapter.MejaAdapter
import com.example.uklcashier.data.AppDatabase3
import com.example.uklcashier.data.entity.Meja
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TmpilMejaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<Meja>()

    private lateinit var database: AppDatabase3
    private lateinit var adapter : MejaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmpil_meja)

        recyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)

        database = AppDatabase3.getInstance(applicationContext)
        adapter = MejaAdapter(list)
        adapter.setDialog(object : MejaAdapter.Dialog{

            override fun onClick(position : Int){
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@TmpilMejaActivity)
                dialog.setTitle(list[position].noMeja)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if(which == 0){
                        //coding ubah
                        val intent = Intent(this@TmpilMejaActivity, MejaActivity::class.java)
                        intent.putExtra("id", list[position].meid)
                        startActivity(intent)
                        //coding hapus
                    }else if (which ==1){
                        database.MejaDao().delete(list[position])
                        getData()
                        //coding batal
                    }else{
                        dialog.dismiss()
                    }
        })
                //menampilkan dialog
                val dialogview = dialog.create()
                dialogview.show()
    }
})
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL,false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, MejaActivity::class.java))

        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.MejaDao().getAll())
        adapter.notifyDataSetChanged()
    }
}