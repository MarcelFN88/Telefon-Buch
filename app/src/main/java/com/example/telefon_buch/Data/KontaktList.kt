package com.example.telefon_buch.Data

import com.example.telefon_buch.R

object KontaktList {

    private val contacts = mutableListOf<Kontakt>()

    fun getContacts(): List<Kontakt> {
        return listOf(
            Kontakt(
                name = "Max",
                surname = "Mustermann",
                phoneNumber = "+49 123 4567890",
                email = "max@example.com",
                address = "12345 Berlin, Mustermannstraße 123",
                contactType = KontaktTyp.Arbeitskollege,
                photoResId = R.drawable.contact,
                birthday = "1990-01-01",
            ),
            Kontakt(
                name = "Maria",
                surname = "Musterfrau",
                phoneNumber = "+49 234 5678901",
                email = "maria@example.com",
                address = "12345 Berlin, Musterweg 456",
                contactType = KontaktTyp.Arbeitskollege,
                photoResId = R.drawable.contact,
                birthday = "1990-01-01",
            )
            // Füge weitere Kontakte nach Bedarf hinzu
        )
        return contacts
    }

    fun addContact(newContact: Kontakt) {
        contacts.add(newContact)
    }

}
