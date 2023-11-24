package com.example.telefon_buch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telefon_buch.Data.Kontakt
import com.example.telefon_buch.Data.KontaktTyp


class KontaktAdapter(private var contactList: List<Kontakt>) :
    RecyclerView.Adapter<KontaktAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.contactName)
        val phoneTextView: TextView = view.findViewById(R.id.contactPhone)
        val photoImageView: ImageView = view.findViewById(R.id.contactPhoto)
        val emailTextView: TextView = view.findViewById(R.id.contactEmail)
        val addressTextView: TextView = view.findViewById(R.id.contactAddress)
        val birthdayTextView: TextView = view.findViewById(R.id.contactBirthday)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kontakt_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.nameTextView.text = "${contact.name} ${contact.surname}"
        holder.phoneTextView.text = contact.phoneNumber
        holder.photoImageView.setImageResource(contact.photoResId)
        holder.emailTextView.text = contact.email
        holder.addressTextView.text = contact.address
        holder.birthdayTextView.text = contact.birthday

        val contactTypeSpinner: Spinner = holder.itemView.findViewById(R.id.KontaktTyp)
        val contactTypes = KontaktTyp.values().map { it.name }
        val spinnerAdapter = ArrayAdapter(
            holder.itemView.context,
            android.R.layout.simple_spinner_item,
            contactTypes
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        contactTypeSpinner.adapter = spinnerAdapter


        contactTypeSpinner.setSelection(contactTypes.indexOf(contact.contactType.name))

        contactTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Update the contact type based on selection
                val selectedType = KontaktTyp.valueOf(contactTypes[position])
                contact.contactType = selectedType
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Handle no selection
            }
        }
    }

    override fun getItemCount() = contactList.size
    fun updateContacts(newContacts: List<Kontakt>) {
        contactList = newContacts
        notifyDataSetChanged()
    }
}
