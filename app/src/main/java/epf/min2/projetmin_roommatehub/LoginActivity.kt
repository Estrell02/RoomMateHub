package epf.min2.projetmin_roommatehub

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Layout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val username = findViewById<EditText>(R.id.login_username)
        val password = findViewById<EditText>(R.id.login_password)
        val loginButton = findViewById<Button>(R.id.button_login)
        val linkNewUser = findViewById<TextView>(R.id.link_newUser)
        val loginLayout = findViewById<LinearLayout>(R.id.login_layout)

        loginButton.setOnClickListener {
            if (isValidCredentials(username.text.toString(), password.text.toString())) {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,"Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG).show()
            }
        }

        loginLayout.setOnClickListener {
            Toast.makeText(this, "Test", Toast.LENGTH_LONG).show()

            username.isEnabled = false
            username.isEnabled = true
            password.isEnabled = false
            password.isEnabled = true
        }

        linkNewUser.setOnClickListener {
            val intent = Intent(this@LoginActivity, CreateUserActivity::class.java)
            startActivity(intent)
        }
    }
    private fun isValidCredentials(username: String, password: String): Boolean {
        //return username.isNotEmpty() && password.isNotEmpty()
        return username == "thomas" && password == "the best"
    }


}
