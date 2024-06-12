package epf.min2.projetmin_roommatehub.ui.List_Annonces

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epf.min2.projetmin_roommatehub.R

class List_annonce_Fragment : Fragment() {

    companion object {
        fun newInstance() = List_annonce_Fragment()
    }

    private lateinit var viewModel: ListAnnonceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_annonce, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListAnnonceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}