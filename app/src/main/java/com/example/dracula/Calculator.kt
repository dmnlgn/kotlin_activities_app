package com.example.dracula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*
import java.lang.Double.parseDouble

class Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        buttonAdd.setOnClickListener() {
            val firstVal: String = editNumberOne.text.toString()
            val secondVal: String = editNumberTwo.text.toString()

            if (firstVal.isNotEmpty() && secondVal.isNotEmpty()) {
                val result = add(firstVal, secondVal)
                textResult.setText("  " + result)
            } else {
                Toast.makeText(this, "one of the input is empty", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSub.setOnClickListener() {
            val firstVal: String = editNumberOne.text.toString()
            val secondVal: String = editNumberTwo.text.toString()

            if (firstVal.isNotEmpty() && secondVal.isNotEmpty()) {
                val result = subs(firstVal, secondVal)
                textResult.setText("  " + result)
            } else {
                Toast.makeText(this, "one of the input is empty", Toast.LENGTH_SHORT).show()
            }
        }

        buttonMulti.setOnClickListener() {
            val firstVal: String = editNumberOne.text.toString()
            val secondVal: String = editNumberTwo.text.toString()

            if (firstVal.isNotEmpty() && secondVal.isNotEmpty()) {
                val result = multi(firstVal, secondVal)
                textResult.setText("  " + result)
            } else {
                Toast.makeText(this, "one of the input is empty", Toast.LENGTH_SHORT).show()
            }
        }

        buttonDivide.setOnClickListener() {
            val firstVal: String = editNumberOne.text.toString()
            val secondVal: String = editNumberTwo.text.toString()

            if (firstVal.isNotEmpty() && secondVal.isNotEmpty()) {
                val result = divide(firstVal, secondVal)
                textResult.setText("  " + result)
            } else {
                Toast.makeText(this, "one of the input is empty", Toast.LENGTH_SHORT).show()
            }
        }

        buttonPam.setOnLongClickListener() {
            Toast.makeText(this, "PROJEKTOWANIE APLIKACJI MOBILNYCH", Toast.LENGTH_SHORT).show()
            true
        }

        buttonReset.setOnClickListener() {
            editNumberOne.setText("")
            editNumberTwo.setText("")
            textResult.setText("")
        }
    }

    private fun divide(firstVal: String, secondVal: String): Double {
        val a = parseDouble(firstVal)
        val b = parseDouble(secondVal)

        val divide: Double = a / b

        return divide
    }

    private fun multi(firstVal: String, secondVal: String): Double {
        val a = parseDouble(firstVal)
        val b = parseDouble(secondVal)

        val multi: Double = a * b

        return multi
    }

    private fun subs(firstVal: String, secondVal: String): Double {
        val a = parseDouble(firstVal)
        val b = parseDouble(secondVal)

        val sub: Double = a - b

        return sub
    }

    private fun add(firstVal: String, secondVal: String): Double {
        val a = parseDouble(firstVal)
        val b = parseDouble(secondVal)

        val sum: Double = a + b

        return sum
    }
}