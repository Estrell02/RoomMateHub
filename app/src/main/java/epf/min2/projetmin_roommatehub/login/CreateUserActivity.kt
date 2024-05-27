package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ApiService
import kotlinx.coroutines.runBlocking
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

        //val createUserLayout = findViewById<LinearLayout>(R.id.create_user_layout)//Todo

        val apiManager = ApiManager()

        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextPseudo.text.toString()
            val mdp = editTextMDP.text.toString()
            val confirmationMdp = editTextConfirmationMDP.text.toString()



            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && mdp.isNotEmpty() && confirmationMdp.isNotEmpty()) {
                if (mdp.equals(confirmationMdp)) {

                    val newUser = NewUser(nom, username, prenom, email, mdp)


                    runBlocking {
                        var response: Response<Unit> = apiManager.createUser(newUser)
                        if (response.isSuccessful){
                            Toast.makeText(this@CreateUserActivity, "Inscription réussie", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@CreateUserActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            println(response.errorBody())
                            Toast.makeText(this@CreateUserActivity, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                }
                if (!mdp.equals(confirmationMdp)){
                    Toast.makeText(this, "Le mot de passe et sa confirmation ne sont pas identique", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }
    }


    /*create_user_layout.setOnClickListener {
        username.isEnabled = false
        username.isEnabled = true
        password.isEnabled = false
        password.isEnabled = true
    }*///Todo
}
