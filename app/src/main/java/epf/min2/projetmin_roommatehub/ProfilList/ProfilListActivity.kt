package epf.min2.projetmin_roommatehub.ProfilList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ClientAdapter

class ProfilListActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilList_layout)

        val apiManager = ApiManager()

        apiManager.getProfils(object : ApiManager.ApiListener<List<Profil>> {
            override fun onSuccess(data: List<Profil>) {
                runOnUiThread {
                    val recyclerView = findViewById<RecyclerView>(R.id.home_recyclerview)

                    recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = ProfilAdapter(data)
                }
            }

            override fun onFailure(error: String) {
                println("Erreur: $error")
            }
        })*/
}