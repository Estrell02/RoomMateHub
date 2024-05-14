package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_layout)

        val editTextNom = findViewById<EditText>(R.id.editTextNom)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenom)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPseudo = findViewById<EditText>(R.id.editTextPseudo)
        val editTextMDP = findViewById<EditText>(R.id.editTextMDP)
        val editTextConfirmationMDP = findViewById<EditText>(R.id.editTextConfirmationMDP)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        val apiListener = object : ApiManager.ApiListener<Unit> {
            override fun onSuccess(data: Unit) {
                Toast.makeText(this@CreateUserActivity, "Inscription réussie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CreateUserActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onFailure(error: String) {
                Toast.makeText(this@CreateUserActivity, "Username déjà pris", Toast.LENGTH_SHORT).show()
            }
        }


        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextPseudo.text.toString()
            val mdp = editTextMDP.text.toString()
            val confirmationMdp = editTextConfirmationMDP.text.toString()


            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && mdp.isNotEmpty() && confirmationMdp.isNotEmpty()) {
                if (isEmailValid(email) && mdp.equals(confirmationMdp) && isPasswordValid(mdp)) {

                    val newUser = NewUser(nom, username, prenom, email, mdp)

                    val apiManager: ApiManager
                    apiManager = ApiManager()

                    apiManager.createUser(newUser,apiListener)

                }
                if (!isEmailValid(email)){
                    Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
                }
                if (!mdp.equals(confirmationMdp)){
                    Toast.makeText(this, "Le mot de passe et sa confirmation ne sont pas identique", Toast.LENGTH_SHORT).show()
                }
                if (!isPasswordValid(mdp)){
                Toast.makeText(this, "Le mot de passe n'est pas valide", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        val containsAtAndDot = email.contains('@') && email.contains('.')
        if (!containsAtAndDot) {
            return false
        }

        val parts = email.split('@')
        val domainParts = parts[1].split('.')
        if (domainParts.size < 2) {
            return false
        }

        val domain = domainParts[0]
        if (domain.isEmpty()) {
            return false
        }

        val extension = domainParts[1]
        if (extension.length < 2) {
            return false
        }

        return true
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) {
            return false
        }

        var hasLetter = false
        var hasDigit = false

        for (char in password) {
            if (char.isLetter()) {
                hasLetter = true
            } else if (char.isDigit()) {
                hasDigit = true
            }
        }

        if (!hasLetter || !hasDigit) {
            return false
        }

        if (password.all { it.isDigit() }) {
            return false
        }

        return true
    }
}
