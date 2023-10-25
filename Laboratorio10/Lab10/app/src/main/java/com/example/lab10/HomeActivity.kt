package com.example.lab10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {
    lateinit var textViewProveedor:TextView
    lateinit var textViewEmail:TextView
    lateinit var btnCerrarsesion:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        textViewProveedor = findViewById<TextView>(R.id.textViewProveedor)
        textViewEmail = findViewById<TextView>(R.id.textViewEmail)
        btnCerrarsesion = findViewById<Button>(R.id.btnCerrarsesion)

        val bundle: Bundle? = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()
    }

    private fun setup(email: String, provider: String){
        title = "Inicio"
        textViewEmail.text = email
        textViewProveedor.text = provider
        btnCerrarsesion.setOnClickListener{
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }

}