package com.example.lab3pm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad secundaria
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        /**
         * Listener para cada boton que nos lleva a cada actividad
         */

        val btnSubMenu1: Button = findViewById(R.id.btnSubMenu1)
        btnSubMenu1.setOnClickListener {
            val intent = Intent(this@SecondActivity, IMG1ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu2: Button = findViewById(R.id.btnSubMenu2)
        btnSubMenu2.setOnClickListener {
            val intent = Intent(this@SecondActivity, IMG2ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu3: Button = findViewById(R.id.btnSubMenu3)
        btnSubMenu3.setOnClickListener {
            val intent = Intent(this@SecondActivity, IMG3ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu4: Button = findViewById(R.id.btnSubMenu4)
        btnSubMenu4.setOnClickListener {
            val intent = Intent(this@SecondActivity, IMG4ACT::class.java)
            startActivity(intent)
        }

    }
}

