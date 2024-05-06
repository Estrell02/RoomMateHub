package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateUserActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.38.241.241:8000/rmh/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
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


        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextPseudo.text.toString()
            val mdp = editTextMDP.text.toString()
            val confirmationMdp = editTextConfirmationMDP.text.toString()


            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && mdp.isNotEmpty() && confirmationMdp.isNotEmpty()) {
                if (email.contains("@") && mdp.equals(confirmationMdp)) {

                    val user = User(nom, username, prenom, email, mdp)


                    /*
                    println(user)

                    val gson: Gson = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                        .setPrettyPrinting()
                        .create()
                    val json = gson.toJson(user)

                    println(json)
                    */


                    apiService.createUser(user).enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@CreateUserActivity, "Inscription réussie", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@CreateUserActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                println(response.message())
                                Toast.makeText(this@CreateUserActivity, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Toast.makeText(this@CreateUserActivity, "Erreur lors de la requête", Toast.LENGTH_SHORT).show()
                        }
                    })

                } else if (mdp.equals(confirmationMdp)){
                    Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
                } else if (email.contains("@")){
                    Toast.makeText(this, "Le mot de passe et sa confirmation ne sont pas identique", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "Le mot de passe et sa confirmation ne sont pas identique et l'email n'est pas valide", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
