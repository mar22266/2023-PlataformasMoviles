package com.example.api

/**
 * Andre Marroquin laboratorio 5
 * Plataformas moviles
 * 22266
 */
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.api.databinding.ActivityMain2Binding
import com.example.api.databinding.ActivityMainBinding
import org.json.JSONObject
import kotlin.math.log

class MainActivity2 : AppCompatActivity() {
    val url: String = "https://meme-api.com/gimme"
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getMeme()
        binding.nextmeme.setOnClickListener {
            getMeme()
        }
    }

    fun getMeme() {
        val progres = ProgressDialog(this)
        progres.setMessage("Loading...")
        progres.show()

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
                progres.dismiss()
            },
            { error->
                progres.dismiss()
                Toast.makeText(this@MainActivity2,"${error.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(stringRequest)
    }

}