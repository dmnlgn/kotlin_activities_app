package com.example.dracula

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_countertimer.*
import java.util.*
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import android.app.TimePickerDialog.OnTimeSetListener
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_calculator.*
import java.util.Calendar
import android.media.RingtoneManager

import android.media.Ringtone
import android.net.Uri


class CountdownTimer : AppCompatActivity() {
    //private val firstVal = editNumber.text.toString().toInt()
    //private var seconds = 0
    var ringtone: Ringtone? = null

    private var running = false
    var seconds = 0

    var time: TextView? = null

    var newHour = 0
    var newMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dracula.R.layout.activity_countertimer)
        if (savedInstanceState != null) {
            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds = savedInstanceState
                .getInt("seconds")
            running = savedInstanceState
                .getBoolean("running")
        }
        countDownTimer()
        //  initiate the edit text
        time = findViewById<View>(R.id.time) as EditText
        // perform click event listener on edit text
        (time as EditText).setOnClickListener(View.OnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute ->
                    running = false
                    seconds = 0
                    newHour = selectedHour
                    newMinute = selectedMinute
                    val formatTime = String.format(
                        Locale.getDefault(),
                        "%d:%02d:%02d", selectedHour,
                        selectedMinute, seconds
                    )
                    (time as EditText).setText(formatTime)
                },
                hour,
                minute,
                true
            ) //Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })

    }

    override fun onSaveInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState
            .putInt("seconds", seconds)
        savedInstanceState
            .putBoolean("running", running)
    }

    private fun countDownTimer() {
        val timeView  = findViewById<TextView>(com.example.dracula.R.id.time_view)

        // Creates a new Handler
        val handler = Handler()

        // Call the post() method,
        handler.post(object : Runnable {
            override fun run() {
                // Format the seconds into hours, minutes,
                // and seconds.
                val time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d", newHour,
                    newMinute, seconds
                )

                // Set the text view text.
                timeView.text = time

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds--
                    if (seconds <= 0) {
                        if (newMinute === 0) {
                            checkIfEnd()
                        } else {
                            setSeconds();
                            newMinute -= 1
                        }
                        if (newMinute <= 0 && seconds <= 0) {
                            if (newHour === 0) {
                                checkIfEnd()
                            } else {
                                setMinutes()
                                newHour -= 1
                            }
                        }
                    }
                }
                // with a delay of 1 second.
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun setSeconds() {
        seconds = 59
    }
    private fun setMinutes() {
        newMinute = 59
    }

    private fun getAlarm() {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(applicationContext, notification)
    }

    private fun checkIfEnd(): Boolean {
        if(running) {
            getAlarm()
            ringtone?.play()
            running = false
            Toast.makeText(this, "KONIEC CZASU", Toast.LENGTH_SHORT).show()

        }
        seconds = 0
        newMinute = 0
        newHour = 0
        return false
    }

    fun onClickStart(view: View?) {
        running = true
        //for fast clock
//        if (running) {
//            countDownTimer()
//        }
    }

    fun onClickReset(view: View?) {
        ringtone?.stop()
        running = false
        seconds = 0
        newMinute = 0
        newHour = 0
    }
}


