package com.example.incomeexpenseproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBase(var context: Context) : SQLiteOpenHelper(context,"Account.db",null,1) {
    var modelArray=ArrayList<ModelClass>()
            
    override fun onCreate(db: SQLiteDatabase?) {
        var sql="create table Account (id integer PRIMARY KEY AUTOINCREMENT ,Amount integer,Category text,Payment text,Note text,Calendar text,TypeofTransfer integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun IncomeInsert(
        amount: String,
        categorb: String,
        Payment: String,
        edNote: String,
        date: String,typ:Int
    ) {
        var db = writableDatabase
        var d = ContentValues()
        d.put("Amount", amount)
        d.put("Category", categorb)
        d.put("Payment", Payment)
        d.put("Note", edNote)
        d.put("Calendar", date)
        d.put("TypeofTransfer", typ)
        var IncomeData = db.insert("Account", null, d)
    }

    fun Show(): ArrayList<ModelClass> {
        var db = readableDatabase
//  singale value display    var sql = "select * from ProductTable where ProductName= 'ydhd' "
        var sql = "select * from Account "
        var cursor = db.rawQuery(sql, null)

        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {

                do {

                    var id = cursor.getInt(0)
                    var Amount = cursor.getInt(1)
                    var Category = cursor.getString(2)
                    var Payment = cursor.getString(3)
                    var Note = cursor.getString(4)
                    var Calendar = cursor.getString(5)
                    var TypeofTransfer = cursor.getInt(6)


                    var model = ModelClass(id,Amount,Category,Payment,Note,Calendar,TypeofTransfer)
                    modelArray.add(model)

                } while (cursor.moveToNext())
            }
            return modelArray
        } else {
            return this.modelArray
        }
    }

}