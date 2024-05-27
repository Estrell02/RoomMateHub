package epf.min2.projetmin_roommatehub.ProfilList

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.login.LoginActivity
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ProfilAdapter
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class ProfilListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_list_layout)

        val apiManager = ApiManager()

        runBlocking {
            val response:Response<List<Profil>> = apiManager.getProfils()
            if (response.isSuccessful){
                val profils : List<Profil> = response.body()!!
                val recyclerView = findViewById<RecyclerView>(R.id.profil_recyclerview)
                recyclerView.layoutManager = LinearLayoutManager(this@ProfilListActivity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = ProfilAdapter(profils)
            } else {
                println(response.errorBody())
            }

        }

    }
}