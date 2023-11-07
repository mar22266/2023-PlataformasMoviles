package com.example.laboratorio12

import android.os.Bundle
import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val gridView = findViewById<GridView>(R.id.lista2)

        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        gridView.adapter = adapter

        gridView.numColumns = 2 // Configura el número de columnas

        val isHorizontal = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isHorizontal) {
            gridView.visibility = View.VISIBLE
        } else {
            gridView.visibility = View.GONE
        }
    }
}


