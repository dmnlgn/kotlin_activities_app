package com.example.dracula.kotlindatabase.Model

class Player {

    var id: Int = 0
    var name: String = ""
    var surname: String = ""
    var position: String = ""
    var club: String = ""
    var nationality: String = ""
    var age: Int = 0

    constructor(
        name: String,
        surname: String,
        position: String,
        club: String,
        nationality: String,
        age: Int
    ) {
        this.name = name
        this.surname = surname
        this.position = position
        this.club = club
        this.nationality = nationality
        this.age = age
    }

    constructor(
        id: Int,
        name: String,
        surname: String,
        position: String,
        club: String,
        nationality: String,
        age: Int
    ) {
        this.id = id
        this.name = name
        this.surname = surname
        this.position = position
        this.club = club
        this.nationality = nationality
        this.age = age
    }
    constructor()
}