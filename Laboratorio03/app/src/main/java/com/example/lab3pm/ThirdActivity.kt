package com.example.lab3pm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad tercera
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        /**
         * Listener para cada boton que nos lleva a cada actividad
         */

        val btnSubMenu21: Button = findViewById(R.id.btnSubMenu21)
        btnSubMenu21.setOnClickListener {
            val intent = Intent(this@ThirdActivity, IMG5ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu22: Button = findViewById(R.id.btnSubMenu22)
        btnSubMenu22.setOnClickListener {
            val intent = Intent(this@ThirdActivity, IMG6ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu23: Button = findViewById(R.id.btnSubMenu23)
        btnSubMenu23.setOnClickListener {
            val intent = Intent(this@ThirdActivity, IMG7ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu24: Button = findViewById(R.id.btnSubMenu24)
        btnSubMenu24.setOnClickListener {
            val intent = Intent(this@ThirdActivity, IMG8ACT::class.java)
            startActivity(intent)
        }
    }

}