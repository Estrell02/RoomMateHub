package epf.min2.projetmin_roommatehub.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.databinding.FragmentHomeBinding
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ProfilAdapter
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.profilRecyclerview
        val completeProfileButton: Button = binding.completeProfileButton

        // Configure le GridLayoutManager pour 2 colonnes
        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager

        // Récupération des profils
        val apiManager = ApiManager()
        runBlocking {
            val response: Response<List<Profil>> = apiManager.getProfils()
            if (response.isSuccessful) {
                val profils: List<Profil> = response.body()!!
                recyclerView.adapter = ProfilAdapter(profils)
            } else {
                println(response.errorBody())
            }
        }

        // Configurer l'animation de gauche
        recyclerView.itemAnimator = SlideInLeftAnimator()

        // Bouton Compléter le profil
        completeProfileButton.setOnClickListener {
            //la page pour modifier le profil
//            val intent = Intent(context, EditProfileActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
