package com.example.utsintentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnHapus = findViewById<Button>(R.id.btnHapus)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val size = sharedPref.getInt("size", 0)

        val stringBuilder = StringBuilder()

        for (i in 0 until size) {
            val email = sharedPref.getString("email_$i", "N/A")
            val password = sharedPref.getString("password_$i", "N/A")
            val nama = sharedPref.getString("nama_$i", "N/A")
            val kelamin = sharedPref.getString("kelamin_$i", "N/A")
            val itemBis = sharedPref.getString("pilihanbis_$i", "N/A")
            val itemKota = sharedPref.getString("kotatujuan_$i", "N/A")
            val deskripsi = sharedPref.getString("deskripsi_$i", "N/A")

            stringBuilder.append("Email: $email\nPassword: $password\nNama: $nama\nKelamin: $kelamin\nPilihan Bis: $itemBis\nKota Tujuan: $itemKota\nDeskripsi: $deskripsi\n\n")
        }

        tvResult.text = stringBuilder.toString()

        btnBack.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        btnHapus.setOnClickListener {
            if (size > 0) {
                val editor = sharedPref.edit()
                val newSize = size - 1

                editor.remove("email_$newSize")
                editor.remove("password_$newSize")
                editor.remove("nama_$newSize")
                editor.remove("kelamin_$newSize")
                editor.remove("pilihanbis_$newSize")
                editor.remove("kotatujuan_$newSize")
                editor.remove("deskripsi_$newSize")
                editor.putInt("size", newSize)
                editor.apply()

                finish()
                startActivity(intent)
            }
        }
    }
}
