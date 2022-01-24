package com.example.dracula

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stopwatch.*
import java.util.*

class Stopwatch : AppCompatActivity() {

    private var seconds = 0

    private var running = false
    private var wasRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dracula.R.layout.activity_stopwatch)
        if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            seconds = savedInstanceState
                .getInt("seconds")
            running = savedInstanceState
                .getBoolean("running")
            wasRunning = savedInstanceState
                .getBoolean("wasRunning")
        }
        runTimer()
    }

    // Save the state of the stopwatch
    // if it's about to be destroyed.
    override fun onSaveInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState
            .putInt("seconds", seconds)
        savedInstanceState
            .putBoolean("running", running)
        savedInstanceState
            .putBoolean("wasRunning", wasRunning)
    }

    // If the activity is paused,
    // stop the stopwatch.
    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    // If the activity is resumed,
    // start the stopwatch
    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    // Start the stopwatch running
    // when the Start button is clicked.
    fun onClickStart(view: View?) {
        running = true
    }

    // Stop the stopwatch running
    // when the Stop button is clicked.
    fun onClickStop(view: View?) {
        running = false
    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    fun onClickReset(view: View?) {
        val records  = findViewById<TextView>(com.example.dracula.R.id.records)
        records.setText("")
        running = false
        seconds = 0
    }

    fun onClickNext(view: View?) {
        val records  = findViewById<TextView>(com.example.dracula.R.id.records)
        val timeView  = findViewById<TextView>(com.example.dracula.R.id.time_view)
        val nextTime = timeView.text
        if (running) {
            records.text = records.text.toString() + nextTime + "\n"
        }
    }

    // Sets the NUmber of seconds on the timer.
    private fun runTimer() {

        // Get the text view.
        val timeView  = findViewById<TextView>(com.example.dracula.R.id.time_view)

        // Creates a new Handler
        val handler = Handler()

        // Call the post() method,
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 360000
                val minutes = seconds / 3600
                val secs = seconds % 3600 / 60
                val milisecs = seconds % 60

                // Format the seconds into hours, minutes,
                // and seconds.
                val time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d:%02d", hours,
                    minutes, secs, milisecs
                )

                // Set the text view text.
                timeView.text = time

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++
                }

                // with a delay of 1 second.
                handler.postDelayed(this, 10)
            }
        })
    }
}