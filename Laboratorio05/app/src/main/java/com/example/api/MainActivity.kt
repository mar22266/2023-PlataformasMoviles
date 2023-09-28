package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.api.databinding.ActivityMainBinding
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    val url: String = "https://meme-api.com/gimme"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMeme()
        binding.nextmeme.setOnClickListener {
            getMeme()
        }

    }

    fun getMeme() {
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.e("Response", "getMeme:" +response.toString())

                var resoneObject=JSONObject(response)

                resoneObject.get("postLink")
                binding.memetitle.text=resoneObject.getString("title")
                binding.memeauthor.text=resoneObject.getString("author")

                Glide.with(this).load(resoneObject.get("url")).into(binding.memeimage)
            },
            { error->
                Toast.makeText(this@MainActivity,"${error.localizedMessage}",Toast.LENGTH_SHORT)
                .show()
            })

        queue.add(stringRequest)
    }
}