package com.example.dracula.kotlindatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dracula.R
import com.example.dracula.kotlindatabase.Model.Player
import com.example.dracula.kotlindatabase.DataBase.DataBaseHandler

class KotlinDatabase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
    }


    fun addPlayer(view: View) {
        val playerName = findViewById<EditText>(R.id.editTextName)
        val playerSurname = findViewById<EditText>(R.id.editTextSurname)
        val playerPosition = findViewById<EditText>(R.id.editTextPosition)
        val playerClub = findViewById<EditText>(R.id.editTextClub)
        val playerNationality = findViewById<EditText>(R.id.editTextNationality)
        val playerAge = findViewById<EditText>(R.id.editTextAge)

        val dataBaseHandler = DataBaseHandler(this, null, null, 1)

        if (playerName.text.toString().trim().isNotEmpty() &&
            playerName.text.toString().trim().isNotEmpty() &&
            playerPosition.text.toString().trim().isNotEmpty() &&
            playerClub.text.toString().trim().isNotEmpty() &&
            playerNationality.text.toString().trim().isNotEmpty() &&
            playerAge.text.toString().trim().isNotEmpty()
        ) {
            val player = Player(
                playerName.text.toString(),
                playerSurname.text.toString(),
                playerPosition.text.toString(),
                playerClub.text.toString(),
                playerNationality.text.toString(),
                playerAge.text.toString().toInt()
            )

            dataBaseHandler.addPlayer(player)
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "NIEKTÓRE POLA NIE SĄ WYPEŁNIONE!", Toast.LENGTH_SHORT).show()
        }
    }

    fun showPlayer(view: View) {
        val playerName = findViewById<EditText>(R.id.editTextName)
        val playerSurname = findViewById<EditText>(R.id.editTextSurname)

        val showPlayers = findViewById<TextView>(R.id.showPlayers)

        val dataBaseHandler = DataBaseHandler(this, null, null, 1)

        val player = dataBaseHandler.findPlayer(playerName.text.toString(), playerSurname.text.toString())

        if (player != null) {
            val playerNameToHtml = playerName.getText().toString()
            val playerSurnameToHtml = playerSurname.getText().toString()
            val playerPositionToHtml = player.position
            val playerClubToHtml = player.club
            val playerNationalityToHtml = player.nationality
            val playerAgeToHtml = player.age.toString()

            showPlayers.setText(
                Html.fromHtml(
                "<p>IMIĘ: $playerNameToHtml</p>" +
                        "<p>NAZWISKO: $playerSurnameToHtml</p>" +
                        "<p>POZYCJA: $playerPositionToHtml</p>" +
                        "<p>KLUB: $playerClubToHtml</p>" +
                        "<p>NARODOWOŚĆ: $playerNationalityToHtml</p>" +
                        "<p>WIEK: $playerAgeToHtml</p>"
                ));

            Toast.makeText(this, "Data loaded!", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Unknown player!", Toast.LENGTH_SHORT).show()
        }
    }

   fun deletePlayer(view: View) {
       val playerName = findViewById<EditText>(R.id.editTextName)
       val playerSurname = findViewById<EditText>(R.id.editTextSurname)

       val dataBaseHandler = DataBaseHandler(this, null, null, 1)

       val result = dataBaseHandler.deletePlayer(playerName.text.toString(), playerSurname.text.toString())

       if (result) {
           Toast.makeText(this, "Player deleted!", Toast.LENGTH_SHORT).show()
       } else {
           Toast.makeText(this, "Unknown player!", Toast.LENGTH_SHORT).show()
       }
   }
    fun resetData(view: View) {
        val playerName = findViewById<EditText>(R.id.editTextName)
        val playerSurname = findViewById<EditText>(R.id.editTextSurname)
        val playerPosition = findViewById<EditText>(R.id.editTextPosition)
        val playerClub = findViewById<EditText>(R.id.editTextClub)
        val playerNationality = findViewById<EditText>(R.id.editTextNationality)
        val playerAge = findViewById<EditText>(R.id.editTextAge)
        val showPlayers = findViewById<TextView>(R.id.showPlayers)

        playerName.setText("")
        playerSurname.setText("")
        playerPosition.setText("")
        playerClub.setText("")
        playerNationality.setText("")
        playerAge.setText("")

        showPlayers.setText("")
    }

    fun showAllPlayers(view: View) {
        val showPlayers = findViewById<TextView>(R.id.showPlayers)
        showPlayers.setText("")
        val dataBaseHandler = DataBaseHandler(this, null, null, 1)
        val playersArray = dataBaseHandler.showAllPlayers()

        if (playersArray != null) {
            for (element in playersArray){
                showPlayers.text = showPlayers.text.toString() + Html.fromHtml(
                    "<p> [${element.id}] </p>" +
                    "<p>IMIĘ: ${element.name}</p>" +
                    "<p>NAZWISKO: ${element.surname}</p>" +
                    "<p>POZYCJA: ${element.position}</p>" +
                    "<p>KLUB: ${element.club}</p>" +
                    "<p>NARODOWOŚĆ: ${element.nationality}</p>" +
                    "<p>WIEK: ${element.age}</p>" +
                    "<br>"
                )
            }
            Toast.makeText(this, "Data loaded!", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Unknown player!", Toast.LENGTH_SHORT).show()
        }
    }
}