package com.example.incomeexpenseproject

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incomeexpenseproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        caterty()
        navigation()
    }

    private fun navigation() {



        binding.imgmenubar.setOnClickListener {
            binding.lnrDrawerLaout.openDrawer(Gravity.LEFT)
        }
    }


    private fun caterty() {
        binding.leycatery.setOnClickListener {
            var intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
        binding.txtviewcate.setOnClickListener {
            var intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }
        binding.leraddincome.setOnClickListener {
            var intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("Key", "Income")
            startActivity(intent)
        }
        binding.lnrExpense.setOnClickListener {
            var intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("Key", "Expense")
            startActivity(intent)
        }
        binding.txExit.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirm Exit!!")
            builder.setMessage("Are you sure to exit ?")
            builder.setPositiveButton(
                "Yes"
            ) { dialogInterface, i -> finish() }
            builder.setNegativeButton(
                "No"
            ) { dialogInterface, i -> dialogInterface.cancel() }
            builder.setCancelable(false)
            builder.show()
        }

        binding.imgshare.setOnClickListener {
            var packagename = applicationContext.packageName;
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packagename")
            )
            startActivity(intent)
        }

        binding.imgcate.setOnClickListener {

            var i= Intent(this,CategoryActivity::class.java)
            startActivity(i)
        }
//hjkvhjkvb
        binding.imgrate.setOnClickListener {

            var packagename = applicationContext.packageName;
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packagename")
            )
            startActivity(intent)
        }

        binding.txVerison.text= packageManager.getPackageInfo(packageName, 0).versionName.toString()

    }

    override fun onResume() {
        super.onResume()
        var databaseRecord = DataBase(this).Show()

        var adapter = ShowIncomeExpenseadapter(this, databaseRecord)
        var linearManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerShow.layoutManager = linearManager
        binding.recyclerShow.adapter = adapter

    }


}