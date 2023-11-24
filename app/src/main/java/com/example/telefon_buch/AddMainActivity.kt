package com.example.telefon_buch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.telefon_buch.Data.Kontakt
import com.example.telefon_buch.Data.KontaktList
import com.example.telefon_buch.Data.KontaktTyp

class AddMainActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        findViewById<Button>(R.id.buttonOpenCamera).setOnClickListener {
            openCamera()
        }

        findViewById<Button>(R.id.buttonSaveContact).setOnClickListener {
            saveContact()
        }
    }

    private fun openCamera() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openCamera()
                } else {
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

        }
    }

    private fun saveContact() {
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val surname = findViewById<EditText>(R.id.editTextSurname).text.toString()
        val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()
        val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
        val address = findViewById<EditText>(R.id.editTextAddress).text.toString()
        val photoResId = 0
        val birthday = findViewById<EditText>(R.id.editTextBirthday).text.toString()


        val newContact = Kontakt(name, surname, phone,email,address, KontaktTyp.Bekannter, photoResId, birthday)
        KontaktList.addContact(newContact)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
