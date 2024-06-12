package epf.min2.projetmin_roommatehub.AnnonceList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.utils.AnnonceAdapter
import epf.min2.projetmin_roommatehub.utils.ApiManager
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class AnnonceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.annonce_list_layout)

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

    }
}
