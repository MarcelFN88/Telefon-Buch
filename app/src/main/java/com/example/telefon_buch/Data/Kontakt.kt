package com.example.telefon_buch.Data

data class Kontakt(
    var name: String,
    var surname: String,
    var phoneNumber: String,
    var email: String,
    var address: String,
    var contactType: KontaktTyp,
    var photoResId: Int,
    var birthday: String,
)
