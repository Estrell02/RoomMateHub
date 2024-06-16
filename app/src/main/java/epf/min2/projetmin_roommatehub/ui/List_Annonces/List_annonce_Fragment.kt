package epf.min2.projetmin_roommatehub.ui.List_Annonces

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.AnnonceList.CreateAnnonceActivity
import epf.min2.projetmin_roommatehub.DetailAnnonceActivity
import epf.min2.projetmin_roommatehub.MainActivity
import epf.min2.projetmin_roommatehub.utils.AnnonceAdapter
import epf.min2.projetmin_roommatehub.utils.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class List_annonce_Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var apiManager: ApiManager
    private lateinit var adapter: AnnonceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_annonce, container, false)
        recyclerView = view.findViewById(R.id.annonce_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = AnnonceAdapter(emptyList()) { annonce ->
            val intent = Intent(context, DetailAnnonceActivity::class.java)
            intent.putExtra("annonce", Gson().toJson(annonce))
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val buttonCreateAnnonce = view.findViewById<Button>(R.id.buttonCreateAnnonce)
        buttonCreateAnnonce.setOnClickListener {
            // Handle button click to create a new annonce
            val intent = Intent(context, CreateAnnonceActivity::class.java)
            startActivity(intent)
//            finish()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiManager = ApiManager()
        fetchAnnonces()
    }

    private fun fetchAnnonces() {
        lifecycleScope.launch {
            val response: Response<List<Annonce>> = withContext(Dispatchers.IO) {
                apiManager.getAnnonces()
            }
            if (response.isSuccessful) {
                val annonces: List<Annonce> = response.body()!!
                adapter.updateData(annonces)
            } else {
                println(response.errorBody())
                // Handle error appropriately
            }
        }
    }
}
