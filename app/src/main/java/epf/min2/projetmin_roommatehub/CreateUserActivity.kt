package epf.min2.projetmin_roommatehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_layout)

        val editTextNom = findViewById<EditText>(R.id.editTextNom)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenom)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()

            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty()) {
                if (email.contains("@")) {
                    Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CreateUserActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
