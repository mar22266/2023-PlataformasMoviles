package com.example.lab3pm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FourthActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad cuarta
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        /**
         * listener para cada boton que nos lleva a cada actividad
         */
        val btnSubMenu31: Button = findViewById(R.id.btnSubMenu31)
        btnSubMenu31.setOnClickListener {
            val intent = Intent(this@FourthActivity, IMG9ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu32: Button = findViewById(R.id.btnSubMenu32)
        btnSubMenu32.setOnClickListener {
            val intent = Intent(this@FourthActivity, IMG10ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu3: Button = findViewById(R.id.btnSubMenu33)
        btnSubMenu3.setOnClickListener {
            val intent = Intent(this@FourthActivity, IMG11ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu34: Button = findViewById(R.id.btnSubMenu34)
        btnSubMenu34.setOnClickListener {
            val intent = Intent(this@FourthActivity, IMG12ACT::class.java)
            startActivity(intent)
        }
    }
}