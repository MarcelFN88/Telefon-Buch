package com.example.telefon_buch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telefon_buch.Data.KontaktList

class MainActivity : AppCompatActivity() {

    private lateinit var contactsRecyclerView: RecyclerView
    private lateinit var kontaktAdapter: KontaktAdapter
    private lateinit var adapter: KontaktAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.contactsRecyclerView)
        adapter = KontaktAdapter(KontaktList.getContacts())
        recyclerView.adapter = adapter

        contactsRecyclerView = findViewById(R.id.contactsRecyclerView)
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)

        kontaktAdapter = KontaktAdapter(KontaktList.getContacts())
        contactsRecyclerView.adapter = kontaktAdapter

        // Setup button listeners
        findViewById<Button>(R.id.addButton).setOnClickListener { addContact() }
        findViewById<Button>(R.id.deleteButton).setOnClickListener { deleteContact() }
        findViewById<Button>(R.id.sortButton).setOnClickListener { sortContacts() }
    }

    private fun addContact() {
        val intent = Intent(this, AddMainActivity::class.java)
        startActivity(intent)
    }


    private fun deleteContact() {
        // Logic to delete a contact
    }

    private fun sortContacts() {
        // Logic to sort contacts
    }

    override fun onResume() {
        super.onResume()
        adapter.updateContacts(KontaktList.getContacts())
    }
}
