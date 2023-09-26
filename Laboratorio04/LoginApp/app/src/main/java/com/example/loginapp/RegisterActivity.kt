package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_slide)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            val jsonObject = JSONObject()
            jsonObject.put("username", email)
            jsonObject.put("name", username)
            jsonObject.put("password", password)

            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject.toString())

            val request = Request.Builder()
                .url("http://10.0.2.2/loginapp/public/register.php")
                .post(requestBody)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()
                    runOnUiThread {
                        if (response.isSuccessful) {
                            // Manejar la respuesta del servidor, que podría ser un token u otro mensaje
                            Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            try {
                                val json = JSONObject(responseBody)
                                val errorMessage = json.optString("message", "Error en el servidor")
                                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                            } catch (e: JSONException) {
                                Toast.makeText(applicationContext, "Error en el servidor", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            })
        }
    }
}

