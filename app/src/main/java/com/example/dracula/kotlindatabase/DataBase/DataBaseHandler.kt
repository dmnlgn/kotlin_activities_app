package com.example.dracula.kotlindatabase.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.dracula.kotlindatabase.Model.Player
import android.widget.TextView




class DataBaseHandler(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "playersDB.db"
        val TABLE_PLAYERS = "players"

        val COLUMN_ID = "id"
        val COLUMN_PLAYERNAME = "player_name"
        val COLUMN_PLAYERSURNAME = "player_surname"
        val COLUMN_POSITION = "player_position"
        val COLUMN_CLUB = "player_club"
        val COLUMN_NATIONALITY = "player_nationality"
        val COLUMN_AGE = "player_age"
    }

    override fun onCreate(dataBase: SQLiteDatabase?) {
        var CREATE_PLAYERS_TABLE =("CREATE TABLE " +
                TABLE_PLAYERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PLAYERNAME + " TEXT,"
                + COLUMN_PLAYERSURNAME + " TEXT,"
                + COLUMN_POSITION + " TEXT,"
                + COLUMN_CLUB + " TEXT,"
                + COLUMN_NATIONALITY + " TEXT,"
                + COLUMN_AGE + " TEXT" + ")"
                )
        dataBase?.execSQL(CREATE_PLAYERS_TABLE)
    }

//    CREATE TABLE PLAYER(ID INTEGER PRIMARY KEY, PLAYER_SURNAME TEXT...)

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS" + TABLE_PLAYERS)
        onCreate(p0)
    }

    fun addPlayer(player: Player) {
        val values = ContentValues()
        values.put(COLUMN_PLAYERNAME, player.name)
        values.put(COLUMN_PLAYERSURNAME, player.surname)
        values.put(COLUMN_POSITION, player.position)
        values.put(COLUMN_CLUB, player.club)
        values.put(COLUMN_NATIONALITY, player.nationality)
        values.put(COLUMN_AGE, player.age)

        val db = this.writableDatabase
        db.insert(TABLE_PLAYERS, null, values)
        db.close()
    }

    fun findPlayer(playerName: String, playerSurname: String): Player? {
        val query =
            "SELECT * from $TABLE_PLAYERS WHERE $COLUMN_PLAYERNAME = \"$playerName\"" +
                    "and $COLUMN_PLAYERSURNAME = \"$playerSurname\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var player: Player? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val id = cursor.getString(0).toInt()
            val playerNameC = cursor.getString(1)
            val playerSurnameC = cursor.getString(2)
            val playerPosition = cursor.getString(3)
            val playerClub = cursor.getString(4)
            val playerNationality = cursor.getString(5)
            val playerAge = cursor.getString(6).toInt()

            player = Player(id, playerNameC, playerSurnameC, playerPosition, playerClub, playerNationality, playerAge)
            cursor.close()
        }
        db.close()
        return player
    }

    // we have created a new method for reading all the courses.
    fun showAllPlayers(): ArrayList<Player>? {
        // on below line we are creating a
        // database for reading our database.
        val query =
            "SELECT * from $TABLE_PLAYERS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        // on below line we are creating a new array list.
        val playersArray: ArrayList<Player> = ArrayList()

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                playersArray.add(
                    Player(
                        cursor.getString(0).toInt(),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6).toInt()
                    )
                )
            } while (cursor.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close()
        return playersArray
    }


    fun deletePlayer(playerName: String, playerSurname: String): Boolean {
        var result = false

        val query =
            "SELECT * from $TABLE_PLAYERS WHERE $COLUMN_PLAYERNAME = \"$playerName\"" +
                    "and $COLUMN_PLAYERSURNAME = \"$playerSurname\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_PLAYERS, COLUMN_ID + " = ?",
                arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

}