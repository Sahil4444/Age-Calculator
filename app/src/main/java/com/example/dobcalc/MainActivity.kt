package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselectedDate: TextView? = null
    private var tvageInMinutes: TextView? = null
    private var tvageInHours: TextView? = null
    private var tvageInDays: TextView? = null
    private var tvageInMonths: TextView? = null
    private var tvageInYears: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Developed by Sahil Narale", Toast.LENGTH_SHORT).show()
        val btnDataPicker : Button = findViewById(R.id.minuteBtn)
        tvselectedDate = findViewById(R.id.selectedDate)
        tvageInMinutes = findViewById(R.id.ageInMinutes)
        tvageInHours = findViewById(R.id.ageInHours)
        tvageInDays = findViewById(R.id.ageInDays)
        tvageInMonths = findViewById(R.id.ageInMonths)
        tvageInYears = findViewById(R.id.ageInYears)
        btnDataPicker.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){

        val  myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this, "Date: $selectedDayOfMonth . ${selectedMonth+1} . $selectedYear", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvselectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInMinutes = theDate.time / 60000
                val selectedDateInHours = selectedDateInMinutes / 60
                val selectedDateInDays = selectedDateInHours / 24
                val selectedDateInMonths = selectedDateInDays / 30
                val selectedDateInYears = selectedDateInMonths / 12
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val currentDateInHours = currentDateInMinutes / 60
                    val currentDateInDays = currentDateInHours / 24
                    val currentDateInMonths = currentDateInDays / 30
                    val currentDateInYears = currentDateInMonths / 12

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    val differenceInHours = currentDateInHours - selectedDateInHours
                    val differenceInDays = currentDateInDays - selectedDateInDays
                    val differenceInMonths = currentDateInMonths - selectedDateInMonths
                    val differenceInYears = currentDateInYears - selectedDateInYears
                    tvageInMinutes?.text = differenceInMinutes.toString()
                    tvageInHours?.text = differenceInHours.toString()
                    tvageInDays?.text = differenceInDays.toInt().toString()
                    tvageInMonths?.text = differenceInMonths.toInt().toString()
                    tvageInYears?.text = differenceInYears.toInt().toString()
                }
            }
        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}