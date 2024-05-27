package epf.min2.projetmin_roommatehub.utils.OptionBarre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.home.HomeActivity
import epf.min2.projetmin_roommatehub.login.LoginActivity
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ApiService
import epf.min2.projetmin_roommatehub.utils.ClientAdapter
import kotlinx.coroutines.runBlocking
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
        editTextNom.setText(Global.currentUser.user.first_name)
        val editTextPrenom = findViewById<EditText>(R.id.editTextPrenomModify)
        editTextPrenom.setText(Global.currentUser.user.last_name)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmailModify)
        editTextEmail.setText(Global.currentUser.user.email)
        val editTextPseudo = findViewById<EditText>(R.id.editTextPseudoModify)
        editTextPseudo.setText(Global.currentUser.user.username)
        //val editTextMDP = findViewById<EditText>(R.id.editTextMDPModify)
        //val editTextConfirmationMDP = findViewById<EditText>(R.id.editTextConfirmationMDPModify)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmitModify)

        val apiManager = ApiManager()

        buttonSubmit.setOnClickListener {
            val nom = editTextNom.text.toString()
            val prenom = editTextPrenom.text.toString()
            val email = editTextEmail.text.toString()
            val username = editTextPseudo.text.toString()
            //todo val mdpModify = editTextMDP.text.toString()
            //todo val confirmationMdpModi = editTextConfirmationMDP.text.toString()



            if (nom.isNotEmpty() && prenom.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty()) {

                    val user = User(Global.currentUser.user.id,nom, username, prenom, email)
                    runBlocking {
                        val response: Response<Unit> = apiManager.modifyUser(user)
                        if (response.isSuccessful){
                            Toast.makeText(this@ModifyUserActivity, "Modifictaion réussie", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@ModifyUserActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            println(response.errorBody())
                            Toast.makeText(this@ModifyUserActivity, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
                        }

                    }

            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }

}