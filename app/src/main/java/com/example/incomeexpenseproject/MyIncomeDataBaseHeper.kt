package com.example.incomeexpenseproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyIncomeDataBaseHeper(val context: Context,) :
    SQLiteOpenHelper(context, "Category", null, 1) {
    var myincome = ArrayList<IncomeModalClass>()

    override fun onCreate(db: SQLiteDatabase?) {

        var IncomeData =
            "create table Category(id  integer primary key autoincrement,Categoryame text) "
        db?.execSQL(IncomeData)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(edt: String) {
        var db = writableDatabase
        var d = ContentValues()
        d.put("Categoryame", edt)
        var IncomeData = db.insert("Category", null, d)
    }

    fun display(): ArrayList<IncomeModalClass> {

        var dob = readableDatabase
        var sql = "select  * from  Category"
        var cursor = dob.rawQuery(sql, null)


        if (cursor.count > 0) {

            if (cursor.moveToFirst()) {
                do {

                    var id = cursor.getInt(0)
                    var name = cursor.getString(1)

                    var model=IncomeModalClass(id,name)
                    myincome.add(model)
                } while (cursor.moveToNext())
            }
            return myincome
        } else {

            Toast.makeText(context, "Display", Toast.LENGTH_SHORT).show()
        return myincome
        }
    }
}