package com.example.dracula

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.dracula.kotlindatabase.KotlinDatabase

//import com.example.dracula.kotlindatabase.KotlinDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buttonCalc = findViewById<Button>(R.id.button_calc)
        val buttonStopWtch = findViewById<Button>(R.id.button_stopwatch)
        val buttonCounter = findViewById<Button>(R.id.button_countTimer)
        val buttonDatabase = findViewById<Button>(R.id.button_database)

        buttonCalc.setOnClickListener{
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }
        buttonStopWtch.setOnClickListener{
            val intent = Intent(this, Stopwatch::class.java)
            startActivity(intent)
        }
        buttonCounter.setOnClickListener{
            val intent = Intent(this, CountdownTimer::class.java)
            startActivity(intent)
        }
        buttonDatabase.setOnClickListener{
            val intent = Intent(this, KotlinDatabase::class.java)
            startActivity(intent)
        }
    }
}