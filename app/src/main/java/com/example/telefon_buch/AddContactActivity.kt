package com.example.telefon_buch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.telefon_buch.Data.Kontakt
import com.example.telefon_buch.Data.KontaktTyp

class AddContactActivity : AppCompatActivity() {

    // Request-Code für die Kamera-Intent
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addcontactactivity)

        findViewById<Button>(R.id.buttonOpenCamera).setOnClickListener {
            openCamera()
        }

        findViewById<Button>(R.id.buttonSaveContact).setOnClickListener {
            saveContact()
        }
    }

    private fun openCamera() {
        // Überprüfen, ob die Kamera-Berechtigung gewährt wurde
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Berechtigung anfordern, falls sie noch nicht gewährt wurde
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE)
        } else {
            // Kamera-Intent starten
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // Fehlerbehandlung, falls keine Kamera-App verfügbar ist
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openCamera()
                } else {
                    // Berechtigung wurde verweigert. Benutzer darüber informieren.
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Bitmap des Fotos verarbeiten oder anzeigen
        }
    }

    private fun saveContact() {
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val surname = findViewById<EditText>(R.id.editTextSurname).text.toString()
        // Weitere EditText-Felder auslesen

        // Erstellen Sie ein Kontakt-Objekt mit diesen Daten
        val newContact = Kontakt(name, surname, "", "", "", KontaktTyp.Freund, photoResId = 0,"")

        // Speichern Sie den neuen Kontakt in after Datenquelle (Datenbank, Liste, etc.)
    }

}
