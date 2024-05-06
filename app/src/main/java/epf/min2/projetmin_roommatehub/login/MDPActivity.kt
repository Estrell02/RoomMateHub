package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.utils.InterfaceAPIListener
import epf.min2.projetmin_roommatehub.utils.envoyerEmail


class MDPActivity : AppCompatActivity(), InterfaceAPIListener.ApiListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mdp_layout)

        val editTextEmail = findViewById<EditText>(R.id.mdpEditTextEmail)
        val buttonSubmitEmail = findViewById<Button>(R.id.mdpButtonSubmitEmail)

        val editTextCode = findViewById<EditText>(R.id.mdpEditTextCode)
        val buttonSubmitCode = findViewById<Button>(R.id.mdpButtonSubmitCode)

        buttonSubmitEmail.setOnClickListener {

            if (editTextEmail.text.toString().contains("@")) {
                val apiClient = InterfaceAPIListener(this)
                val url = "https://jsonplaceholder.typicode.com/users"
                apiClient.fetchData(url)
            } else {
                Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
            }

            buttonSubmitCode.setOnClickListener {

                if (editTextCode.text.toString().equals("42")) {
                    val intent = Intent(this@MDPActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Ce code n'est pas valide", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidMail(mail: String, users : List<User>): Boolean {
        var isMailPresent = false

        for (user in users) {
            if (user.email == mail) {
                val destinataire = "thomas.beurdouche@epfedu.fr" // mail
                val sujet = "RoomMateHub MDP Oublié"
                val corps = "Mail = ${mail} Code temporaire = 42"
                envoyerEmail(destinataire, sujet, corps)
                isMailPresent = true
                break
            }
        }
        return isMailPresent
    }

    override fun onSuccess(users: List<User>) {
        val editTextEmail = findViewById<EditText>(R.id.mdpEditTextEmail)

        if (isValidMail(editTextEmail.text.toString(),users)){
            runOnUiThread {
                Toast.makeText(this, "Mail envoyé", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        else{
            runOnUiThread {
            Toast.makeText(this, "Cette adresse mail n'a pas de compte associé", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onFailure(error: String) {
        println("Erreur: $error")
    }

}