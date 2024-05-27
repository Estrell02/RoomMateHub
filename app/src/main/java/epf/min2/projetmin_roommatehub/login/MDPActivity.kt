package epf.min2.projetmin_roommatehub.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.utils.envoyerEmail


class MDPActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mdp_layout)

        val editTextEmail = findViewById<EditText>(R.id.mdpEditTextEmail)
        val buttonSubmitEmail = findViewById<Button>(R.id.mdpButtonSubmitEmail)

        val editTextCode = findViewById<EditText>(R.id.mdpEditTextCode)
        val buttonSubmitCode = findViewById<Button>(R.id.mdpButtonSubmitCode)

        buttonSubmitEmail.setOnClickListener {

            if (isValidMail(editTextEmail.text.toString())) {
                //TODO : faire un appel d'api pour des emails
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

    private fun isValidMail(mail: String): Boolean {

            val destinataire = "thomas.beurdouche@epfedu.fr" // mail
            val sujet = "RoomMateHub MDP Oublié"
            val corps = "Mail = ${mail} Code temporaire = 42"
            envoyerEmail(destinataire, sujet, corps)

        return true
    }


}