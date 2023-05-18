package com.example.uklcashier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcashier.R
import com.example.uklcashier.data.entity.Meja


class MejaAdapter (var list: List<Meja>): RecyclerView.Adapter<MejaAdapter.ViewHolder>(){

    private lateinit var dialog: Dialog

    fun setDialog(dialog:Dialog){
        this.dialog = dialog
    }
    interface Dialog{
        fun onClick(position: Int)
    }


    inner class ViewHolder (view: View):RecyclerView.ViewHolder(view){
         var nomeja : TextView

        init {
            nomeja = view.findViewById(R.id.txtnomeja)

            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_meja,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nomeja.text = list[position].noMeja
    }

    override fun getItemCount(): Int {
        return list.size
    }
}