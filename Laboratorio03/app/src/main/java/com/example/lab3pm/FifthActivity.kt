package com.example.lab3pm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FifthActivity : AppCompatActivity() {

    /**
     * Funcion que crea la actividad quinta
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        /**
         * Listener para cada boton que nos lleva a cada actividad
         */

        val btnSubMenu41: Button = findViewById(R.id.btnSubMenu41)
        btnSubMenu41.setOnClickListener {
            val intent = Intent(this@FifthActivity, IMG13ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu42: Button = findViewById(R.id.btnSubMenu42)
        btnSubMenu42.setOnClickListener {
            val intent = Intent(this@FifthActivity, IMG14ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu43: Button = findViewById(R.id.btnSubMenu43)
        btnSubMenu43.setOnClickListener {
            val intent = Intent(this@FifthActivity, IMG15ACT::class.java)
            startActivity(intent)
        }
        val btnSubMenu44: Button = findViewById(R.id.btnSubMenu44)
        btnSubMenu44.setOnClickListener {
            val intent = Intent(this@FifthActivity, IMG16ACT::class.java)
            startActivity(intent)
        }
    }
}