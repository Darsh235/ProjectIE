package com.example.incomeexpenseproject

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.incomeexpenseproject.databinding.ActivityAddIncomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class AddIncomeActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    var c = Calendar.getInstance()
    lateinit var binding: ActivityAddIncomeBinding
    lateinit var key: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init() {

        var c = Calendar.getInstance();
        var mYear = c.get(Calendar.YEAR);
        var mMonth = c.get(Calendar.MONTH);
        var mDay = c.get(Calendar.DAY_OF_MONTH);
        binding.txtDates.setText(mDay.toString() + "-" + (mMonth + 1) + "-" + mYear)

        key = intent.getStringExtra("Key").toString()

        if (key.equals("Income")) {
            binding.txtTitle.setText(key)
            binding.txtCategory.setText("Other " + key)

        } else if (key.equals("Expense")) {
            binding.txtTitle.setText(key)
            binding.txtCategory.setText("Other " + key)
        }



        binding.lnrCateg.setOnClickListener {

            var list = MyIncomeDataBaseHeper(this).display()
            val bottomDialog = BottomSheetDialog(this).apply {
                val dialogView = LayoutInflater.from(context)
                    .inflate(R.layout.dilog, null, false)
                setContentView(dialogView)
                setOnShowListener {
                    (dialogView.parent as ViewGroup).background = ColorDrawable(Color.TRANSPARENT)
                }
                show()
            }
            var tag = bottomDialog.findViewById<TextView>(R.id.tag)
            tag?.setText("Category")

            var RclShow = bottomDialog.findViewById<RecyclerView>(R.id.RclShow)
            var listAdapetr = ListShowAdapterClass(this, list) { listdata ->
                binding.txtCategory.setText(listdata)
                bottomDialog.dismiss()
            }
            var layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            RclShow?.layoutManager = layout
            RclShow?.adapter = listAdapetr



            bottomDialog.show()
        }
        binding.lnrPAymentM.setOnClickListener {

            val bottomDialog = BottomSheetDialog(this).apply {
                val dialogView = LayoutInflater.from(context)
                    .inflate(R.layout.dilog, null, false)
                setContentView(dialogView)
                setOnShowListener {
                    (dialogView.parent as ViewGroup).background = ColorDrawable(Color.TRANSPARENT)
                }
                show()
            }
            var tag = bottomDialog.findViewById<TextView>(R.id.tag)
            tag?.setText("Payment")

            var ArrayPay = ArrayList<String>()
            ArrayPay.add("Offline")
            ArrayPay.add("Online")


            var RclShow = bottomDialog.findViewById<RecyclerView>(R.id.RclShow)
            var listAdapetr = PayAdapter(this, ArrayPay) { listdata ->
                binding.txtPayment.setText(listdata)
                bottomDialog.dismiss()
            }
            var layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            RclShow?.layoutManager = layout
            RclShow?.adapter = listAdapetr

            bottomDialog.show()

        }

        binding.txtSubmit.setOnClickListener {

            var amount = binding.edAmount.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter the amount", Toast.LENGTH_SHORT).show()
            } else {
                var categorb = binding.txtCategory.text.toString()
                var Payment = binding.txtCategory.text.toString()
                var edNote = binding.txtCategory.text.toString()
                var date = binding.txtDates.text.toString()
                var typ = 0

                var database = DataBase(this)
                if (key == "Expense") {
                    typ = 1
                }
                Toast.makeText(this, "Submited", Toast.LENGTH_SHORT).show()
                database.IncomeInsert(amount, categorb, Payment, edNote, date, typ)

                notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                val intent = Intent(this, MainActivity::class.java)

                // FLAG_UPDATE_CURRENT specifies that if a previous
                // PendingIntent already exists, then the current one
                // will update it with the latest intent
                // 0 is the request code, using it later with the
                // same method again will get back the same pending
                // intent for future reference
                // intent passed here is to our afterNotification class
                val pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(
                        channelId,
                        description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelId)
//                        .setContent(contentView)
                        .setContentTitle("Income Expense")
                        .setContentText("Trancation Add")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .setContentIntent(pendingIntent)
                } else {

                    builder = Notification.Builder(this)
//                        .setContent(contentView)
                        .setContentTitle("Income Expense")
                        .setContentText("Trancation Add")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .setContentIntent(pendingIntent)
                }
                notificationManager.notify(1234, builder.build())
            }

            finish()
        }



        binding.right.setOnClickListener {
            val currentTime = Calendar.getInstance()
            if (c.get(Calendar.DAY_OF_MONTH).equals(currentTime[Calendar.DAY_OF_MONTH]) && c.get(
                    Calendar.MONTH
                ).equals(currentTime[Calendar.MONTH]) && c.get(Calendar.YEAR)
                    .equals(currentTime[Calendar.YEAR])
            ) {
            } else {
                c.add(Calendar.DAY_OF_MONTH, +1)
                c.add(Calendar.MONTH, 0)
                c.add(Calendar.YEAR, 0)
                val currentDatePlusOne = c.get(Calendar.DAY_OF_MONTH)
                val month = c.get(Calendar.MONTH)
                val year = c.get(Calendar.YEAR)
                binding.txtDates.text =
                    (currentDatePlusOne.toString() + "/" + (month + 1) + "/" + year)
            }
        }
        binding.left.setOnClickListener {
            c.add(Calendar.DAY_OF_MONTH, -1)
            c.add(Calendar.MONTH, 0)
            c.add(Calendar.YEAR, 0)
            val currentDatePlusOne = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)
            binding.txtDates.text = (currentDatePlusOne.toString() + "/" + (month + 1) + "/" + year)
        }
        binding.imgCal.setOnClickListener {


            var mYear = c.get(Calendar.YEAR);
            var mMonth = c.get(Calendar.MONTH);
            var mDay = c.get(Calendar.DAY_OF_MONTH);

            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth -> binding.txtDates.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.datePicker.setMaxDate(Date().time)
            datePickerDialog.show()
        }


        binding.imgback.setOnClickListener {
            onBackPressed()
        }

    }

}


