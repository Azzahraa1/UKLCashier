package com.example.uklcashier.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcashier.R
import com.example.uklcashier.data.entity.Menu
import org.w3c.dom.Text

class MenuAdapter(var list: List<Menu>): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private lateinit var dialog2 :Dialog2

    fun setDialog(dialog2:Dialog2){
        this.dialog2 = dialog2
    }
    interface Dialog2{
        fun onClick(position:Int)
    }



    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var namamenu: TextView
        var deskripsi : TextView
        var harga : TextView


        init {
            namamenu = view.findViewById(R.id.txtmenu)
            deskripsi = view.findViewById(R.id.txtdesk)
            harga = view.findViewById(R.id.txtharga)

            view.setOnClickListener{
                dialog2.onClick(layoutPosition)
            }
        }

//        init {
//            namamenu = view.findViewById(R.id.txtmenu)
//            deskripsi = view.findViewById(R.id.txtdesk)
//            harga = view.findViewById(R.id.txtharga)
//
//            view.setOnClickListener{
//                dialog3.onClick2(layoutPosition)
//            }
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu,parent, false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namamenu.text = list[position].namaMenu
        holder.deskripsi.text = list[position].deskripsi
        holder.harga.text = list[position].harga
    }

    override fun getItemCount(): Int {
      return list.size
    }
}