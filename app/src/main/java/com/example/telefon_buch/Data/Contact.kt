package com.example.telefon_buch.Data

data class Contact(
    val contactId: Int,
    val contactName: String,
    val contactSurname: String,
    val contactPhone: String,
    val contactEmail: String,
    val contactAddress: String,
    val contactType: String
)