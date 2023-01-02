package com.example.incomeexpenseproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.incomeexpenseproject.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category()
    }

    private fun category() {
        var income = MyIncomeDataBaseHeper(this)
        binding.btndone.setOnClickListener {
            var edt = binding.EdtCatery.text.toString()

            if (edt.isEmpty()) {
                binding.EdtCatery.error = "Enter Category"
            } else {
                income.insertData(edt)
            }


            var intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)

        }

    }


}