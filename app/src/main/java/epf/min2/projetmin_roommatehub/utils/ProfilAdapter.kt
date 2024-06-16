package epf.min2.projetmin_roommatehub.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.R

class ProfilViewHolder(view : View) : RecyclerView.ViewHolder(view)

class ProfilAdapter(val profils: List<Profil>
                    ) : RecyclerView.Adapter<ProfilViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.profil_view,parent,false)
        return ProfilViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profils.size
    }

    override fun onBindViewHolder(holder: ProfilViewHolder, position: Int) {
        val profil : Profil = profils[position]
        val view : View = holder.itemView
        val profilTextView : TextView = view.findViewById(R.id.profil_textview)
        profilTextView.text = "${profil.user.username} ${profil.user.id}"

        val imageView = view.findViewById<ImageView>(R.id.profil_view_cardview)
        imageView.setImageResource(R.drawable.__rn01p34tq1y84t_duye3cq)

        view.setOnClickListener {
//            onProfilClick(profil)
        }
    }
}