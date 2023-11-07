package com.example.laboratorio12


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ListView

class MainActivity : AppCompatActivity() {


    private var Jugadores = emptyArray<String>()
    lateinit var arrayAdapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val Jugadores = arrayOf(
            "Halaand",
            "Messi",
            "Cristiano Ronaldo",
            "Neymar",
            "Mbappe",
            "Lewandowski",
            "De Bruyne",
            "Kante",
            "Kimmich",
            "Salah",
            "Kane",
            "Modric",
        )

        button.setOnClickListener {
            // Crea una intención para abrir SecondActivity
            val intent = Intent(this, SecondActivity::class.java)

            // Inicia SecondActivity
            startActivity(intent)
        }
        val itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Jugadores)
        val listaJugadores: ListView = findViewById(R.id.lista)
        listaJugadores.adapter = itemsAdapter



    }
}