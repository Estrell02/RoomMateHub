package epf.min2.projetmin_roommatehub.utils.OptionBarre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.login.LoginActivity
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifyUserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_user_layout)

        val editTextNom = findViewById<EditText>(R.id.editTextNomModify)
        editTextNom.setText(Global.currentUser.first_name)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenomModify)
        editTextPrenom.setText(Global.currentUser.last_name)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmailModify)
        editTextEmail.setText(Global.currentUser.email)
        val editTextPseudo = findViewById<EditText>(R.id.editTextPseudoModify)
        editTextPseudo.setText(Global.currentUser.username)
        //val editTextMDP = findViewById<EditText>(R.id.editTextMDPModify)
        //val editTextConfirmationMDP = findViewById<EditText>(R.id.editTextConfirmationMDPModify)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmitModify)


        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextPseudo.text.toString()
            //val mdpModify = editTextMDP.text.toString()
            //val confirmationMdpModi = editTextConfirmationMDP.text.toString()

            val apiListener = object : ApiManager.ApiListener<Unit> {
                override fun onSuccess(data: Unit) {
                    Toast.makeText(this@ModifyUserActivity, "Modifictaion réussie", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ModifyUserActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(error: String) {
                    Toast.makeText(this@ModifyUserActivity, "Username déjà pris", Toast.LENGTH_SHORT).show()
                }
            }


            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty()) {
                if (isEmailValid(email)) {

                    val user = User(Global.currentUser.id,nom, username, prenom, email)

                    val apiManager: ApiManager
                    apiManager = ApiManager()

                    apiManager.modifyUser(user,apiListener)

                }
                if (!isEmailValid(email)){
                    Toast.makeText(this, "Veuillez entrer une adresse email valide", Toast.LENGTH_SHORT).show()
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
}