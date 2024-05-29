package epf.min2.projetmin_roommatehub.AnnonceList

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.utils.AnnonceAdapter
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ProfilAdapter
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.util.Date

class AnnonceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.annonce_list_layout)

        val buttonCreateAnnonce = findViewById<Button>(R.id.buttonCreateAnnonce)

        val apiManager = ApiManager()

        runBlocking {
            val response: Response<List<Annonce>> = apiManager.getAnnonces()
            if (response.isSuccessful){
                val annonces : List<Annonce> = response.body()!!
                val recyclerView = findViewById<RecyclerView>(R.id.annonce_recyclerview)
                recyclerView.layoutManager = LinearLayoutManager(this@AnnonceListActivity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = AnnonceAdapter(annonces)
            } else {
                println(response.errorBody())
            }

        }

        buttonCreateAnnonce.setOnClickListener {
            val intent = Intent(this@AnnonceListActivity, CreateAnnonceActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
