package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewDatePicker:Button = findViewById(R.id.editTextDate)

        viewDatePicker.setOnClickListener {
            datePicker()
        }
    }
    fun datePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var dp = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view, year, month, dayOfMonth->
                val selectedDateText : TextView = findViewById(R.id.selectedDate)
                val ageInMinutes : TextView = findViewById(R.id.ageInMinutes)
                val selectedDate : String = "$dayOfMonth/${month+1}/$year"
                selectedDateText.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
                val theDate = sdf.parse(selectedDate)
                val dateInMinutes = theDate.time/60000
                val currenMinutes = sdf.parse(sdf.format(System.currentTimeMillis())).time/60000
                var differenceInMinutes = currenMinutes-dateInMinutes
                ageInMinutes.text = differenceInMinutes.toString()
            },
            year,
            month,
            day)
        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()

    }
}