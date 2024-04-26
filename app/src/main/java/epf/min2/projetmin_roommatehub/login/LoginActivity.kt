package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.API

class LoginActivity : AppCompatActivity(), API.ApiListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val username = findViewById<EditText>(R.id.login_username)
        val password = findViewById<EditText>(R.id.login_password)
        val loginButton = findViewById<Button>(R.id.button_login)
        val linkNewUser = findViewById<TextView>(R.id.link_newUser)
        val linkMDP = findViewById<TextView>(R.id.link_mdp)
        val loginLayout = findViewById<LinearLayout>(R.id.login_layout)

        loginButton.setOnClickListener {
            val apiClient = API(this)
            val url = "https://jsonplaceholder.typicode.com/users"
            apiClient.fetchData(url)
        }

        loginLayout.setOnClickListener {
            username.isEnabled = false
            username.isEnabled = true
            password.isEnabled = false
            password.isEnabled = true
        }

        linkNewUser.setOnClickListener {
            val intent = Intent(this@LoginActivity, CreateUserActivity::class.java)
            startActivity(intent)
        }

        linkMDP.setOnClickListener {
            val intent = Intent(this@LoginActivity, MDPActivity::class.java)
            startActivity(intent)
        }


    }
    private fun isValidCredentials(username: String, password: String, users : List<User>): Boolean {
        var isUsernamePresent = false

        for (user in users) {
            if (user.username == username && user.id == password) {
                isUsernamePresent = true
                break
            }
        }
        return isUsernamePresent
    }

    override fun onSuccess(users: List<User>) {
        runOnUiThread {
            val username = findViewById<EditText>(R.id.login_username)
            val password = findViewById<EditText>(R.id.login_password)
            if (isValidCredentials(username.text.toString(), password.text.toString(), users)) {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                //finish()
            } else {
                Toast.makeText(this,"Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onFailure(error: String) {
        println("Erreur: $error")
    }



}
