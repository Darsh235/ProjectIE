package com.example.incomeexpenseproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PayAdapter(
    var context: Context,
    var list: ArrayList<String>,
    var getListData: ((String) -> Unit)
) : RecyclerView.Adapter<PayAdapter.MyViewOldr>() {

    class MyViewOldr(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtlistset = itemView.findViewById<TextView>(R.id.txtlistset)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewOldr {
        return MyViewOldr(LayoutInflater.from(context).inflate(R.layout.list_show, parent, false))

    }

    override fun onBindViewHolder(holder: PayAdapter.MyViewOldr, position: Int) {
        holder.txtlistset.setText(list.get(position))
        holder.txtlistset.setOnClickListener {
            getListData.invoke(list.get(position))
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}