package com.example.uklcashier.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcashier.R
import com.example.uklcashier.data.entity.User

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog : Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var namauser : TextView
        var email : TextView
        var pass : TextView
        var phone : TextView

        init {
            namauser = view.findViewById(R.id.txtName)
            email = view.findViewById(R.id.txtEmail)
            pass = view.findViewById(R.id.txtPass)
            phone = view.findViewById(R.id.txtPhone)

            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namauser.text = list[position].namaUser
        holder.email.text = list[position].email
        holder.pass.text = list[position].password
        holder.phone.text = list[position].phone
    }

    override fun getItemCount(): Int {
        return list.size
    }
}