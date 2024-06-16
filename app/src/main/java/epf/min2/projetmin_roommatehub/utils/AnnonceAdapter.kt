package epf.min2.projetmin_roommatehub.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.DetailAnnonceActivity

class AnnonceViewHolder(view: View) : RecyclerView.ViewHolder(view)

class AnnonceAdapter(
    private var annonces: List<Annonce>,
    private val onAnnonceClick: (Annonce) -> Unit
) : RecyclerView.Adapter<AnnonceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.annonce_view, parent, false)
        return AnnonceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return annonces.size
    }

    override fun onBindViewHolder(holder: AnnonceViewHolder, position: Int) {
        val annonce: Annonce = annonces[position]
        val view: View = holder.itemView

        val annonceTitleTextView: TextView = view.findViewById(R.id.annonce_title_textview)
        val annonceTypeChambreTextView: TextView = view.findViewById(R.id.annonce_type_chambre_textview)
        val annonceLocationTextView: TextView = view.findViewById(R.id.annonce_location_textview)
        val annoncePriceTextView: TextView = view.findViewById(R.id.annonce_price_textview)
        val annonceImageView: ImageView = view.findViewById(R.id.annonce_image_imageView)

        annonceImageView.setImageResource(R.drawable.img)
        annonceLocationTextView.text = "${annonce.location}"
        annoncePriceTextView.text = "Prix: ${annonce.price}"
        annonceTitleTextView.text = "${annonce.title}"

        val cardView = view.findViewById<CardView>(R.id.annonce_view_cardview)
        cardView.setOnClickListener {
            onAnnonceClick(annonce)
        }
    }

    fun updateData(newAnnonces: List<Annonce>) {
        annonces = newAnnonces
        notifyDataSetChanged()
    }
}
