package epf.min2.projetmin_roommatehub.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.R

class AnnonceViewHolder(view : View) : RecyclerView.ViewHolder(view)

class AnnonceAdapter(val annonces: List<Annonce>) : RecyclerView.Adapter<AnnonceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.annonce_view,parent,false)
        return AnnonceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return annonces.size
    }

    override fun onBindViewHolder(holder: AnnonceViewHolder, position: Int) {
        val annonce: Annonce = annonces[position]
        val view : View = holder.itemView
        val annonceTextView : TextView = view.findViewById<TextView>(R.id.annonce_textview)
        annonceTextView.text = "${annonce.owner.username} ${annonce.description}"

        val imageView = view.findViewById<ImageView>(R.id.annonce_image_imageView)
        imageView.setImageResource(R.drawable.img)
    }
}