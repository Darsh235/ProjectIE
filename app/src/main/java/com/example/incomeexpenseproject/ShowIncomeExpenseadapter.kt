package com.example.incomeexpenseproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShowIncomeExpenseadapter(val context: Context,val databaseRecord: ArrayList<ModelClass>) : RecyclerView.Adapter<ShowIncomeExpenseadapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        val txtIncome_: TextView = itemView.findViewById(R.id.txtIncome_)
        val txtCategory_: TextView = itemView.findViewById(R.id.txtCategory_)
        val txtExpense_: TextView = itemView.findViewById(R.id.txtExpense_)
        val txtDates_: TextView = itemView.findViewById(R.id.txtDates_)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val v=LayoutInflater.from(context).inflate(R.layout.databse_listed,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtCategory_.setText(databaseRecord.get(position).Category)
        holder.txtDates_.setText(databaseRecord.get(position).Calendar)
        if(databaseRecord.get(position).TypeofTransfer==1){
            holder.txtExpense_.setText(databaseRecord.get(position).Amount.toString())
        }
        else{
            holder.txtIncome_.setText(databaseRecord.get(position).Amount.toString())
        }

    }

    override fun getItemCount(): Int {
        return databaseRecord.size
    }
}