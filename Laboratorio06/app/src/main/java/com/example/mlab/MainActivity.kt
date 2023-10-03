package com.example.mlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var cityRecyclerView: RecyclerView
    private lateinit var cityListAdapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityRecyclerView = findViewById(R.id.cityRecyclerView)
        cityRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador
        cityListAdapter = CityListAdapter()
        cityRecyclerView.adapter = cityListAdapter

        // Realiza la solicitud GET a la URL
        val url = "https://api.teleport.org/api/urban_areas/"
        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                // Imprime la respuesta JSON para verificar su contenido
                Log.d("JSON Response", response.toString())

                // Procesa la respuesta JSON y agrega las ciudades al adaptador
                val cityList: JSONArray? = response.optJSONArray("ua:item")
                if (cityList != null) {
                    Log.d("City List", cityList.toString()) // Agrega este registro de depuración
                    cityListAdapter.setData(cityList)
                } else {
                    Log.d("City List", "City list is null") // Agrega este registro de depuración si cityList es nulo
                }
            },
            { error ->
                // Manejar errores aquí, si es necesario
                Log.e("Volley Error", error.toString())
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}

