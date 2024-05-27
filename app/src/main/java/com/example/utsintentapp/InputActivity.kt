package com.example.utsintentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast

class InputActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val btnOK = findViewById<Button>(R.id.btnOK)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val nama = findViewById<EditText>(R.id.nama)
        val rbLaki2 = findViewById<RadioButton>(R.id.rbLaki2)
        val rbPerempuan = findViewById<RadioButton>(R.id.rbPerempuan)
        val cbItem1 = findViewById<CheckBox>(R.id.Item1)
        val cbItem2 = findViewById<CheckBox>(R.id.Item2)
        val cbItem3 = findViewById<CheckBox>(R.id.Item3)
        val cbItem4 = findViewById<CheckBox>(R.id.Item4)
        val spinnerItemKotaTujuan = findViewById<Spinner>(R.id.ItemKotaTujuan)
        val deskripsi = findViewById<EditText>(R.id.deskripsi)

        // Spinner ItemABCD
        ArrayAdapter.createFromResource(
            this,
            R.array.KotaTujuan,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerItemKotaTujuan.adapter = adapter
        }

        btnOK.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val namaText = nama.text.toString()
            val kelamin = if (rbLaki2.isChecked) "Laki-Laki" else if (rbPerempuan.isChecked) "Perempuan" else ""
            val itemBis = mutableListOf<String>()
            if (cbItem1.isChecked) itemBis.add("Bis Kencana")
            if (cbItem2.isChecked) itemBis.add("Bis Damri")
            if (cbItem3.isChecked) itemBis.add("Bis Tentrem")
            if (cbItem4.isChecked) itemBis.add("Bis Kalisari")
            val itemKota = spinnerItemKotaTujuan.selectedItem.toString()
            val deskripsiText = deskripsi.text.toString()

            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val editor = sharedPref.edit()
            val size = sharedPref.getInt("size", 0)

            editor.putString("email_$size", emailText)
            editor.putString("password_$size", passwordText)
            editor.putString("nama_$size", namaText)
            editor.putString("kelamin_$size", kelamin)
            editor.putString("pilihanbis_$size", itemBis.joinToString(", "))
            editor.putString("kotatujuan_$size", itemKota)
            editor.putString("deskripsi_$size", deskripsiText)
            editor.putInt("size", size + 1)
            editor.apply()

            Toast.makeText(this, "Login Saved", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}
