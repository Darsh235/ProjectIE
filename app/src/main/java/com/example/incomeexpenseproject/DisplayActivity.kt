package com.example.incomeexpenseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incomeexpenseproject.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {

    lateinit var binding: ActivityDisplayBinding
    var helper = MyIncomeDataBaseHeper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initview()
    }

    private fun initview() {

var list=helper.display()
        var AdapterR = CategoryAdpterClass(this, list)
        var laymanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.RecycleView.layoutManager=laymanager
        binding.RecycleView.adapter=AdapterR

        binding.imageback.setOnClickListener {
            onBackPressed()
        }

    }
}