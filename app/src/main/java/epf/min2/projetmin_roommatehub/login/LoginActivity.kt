package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.ApiManager

class LoginActivity : AppCompatActivity() {
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
            val apiManager = ApiManager()
            val newUser = NewUser("", username.text.toString(), "", "", password.text.toString())

            apiManager.logIn(newUser,object : ApiManager.ApiListener<LogIn> {
                override fun onSuccess(data: LogIn) {
                    println(data)
                    runOnUiThread {
                            Toast.makeText(this@LoginActivity, "Connexion réussie", Toast.LENGTH_LONG).show()
                            Global.accessToken = data.access
                            Global.refreshToken = data.refresh
                            init(data.id)
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                    }
                }

                override fun onFailure(error: String) {
                    Toast.makeText(this@LoginActivity,"Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG).show()
                    println("Erreur: $error")
                }
            })
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

    fun init(id:Int){
        val apiManager = ApiManager()

        apiManager.getUser(id.toString(),object : ApiManager.ApiListener<User> {
            override fun onSuccess(data: User) {
                if (true) {
                    println("connecté en tant que " + data.username)
                    Global.currentUser=data
                } else {
                    Toast.makeText(this@LoginActivity,"Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(error: String) {
                println("Erreur: $error")
            }
        })
    }

}
