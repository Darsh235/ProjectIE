package com.example.incomeexpenseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyAdapter(
   var  addIncomeActivity: AddIncomeActivity,
   var list: ArrayList<IncomeModalClass>
) : BaseAdapter() {
    override fun getCount(): Int {
        return  list.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var view=LayoutInflater.from(parent?.context).inflate(R.layout.list_show,parent,false)
        var item=view.findViewById<TextView>(R.id.item)

        item.setText(list.get(position).name)
        return view
    }
}