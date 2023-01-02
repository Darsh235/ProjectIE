package com.example.incomeexpenseproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdpterClass(val context: Context, var list: ArrayList<IncomeModalClass>) :
    RecyclerView.Adapter<CategoryAdpterClass.MyviewHolder>() {

    class MyviewHolder(v: View) : RecyclerView.ViewHolder(v) {

      var Catergotyview =v.findViewById<TextView>(R.id.Catergotyview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        var v= LayoutInflater.from(context).inflate(R.layout.categoryview,parent,false)
        return MyviewHolder(v)

    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.Catergotyview.setText(list.get(position).name)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}