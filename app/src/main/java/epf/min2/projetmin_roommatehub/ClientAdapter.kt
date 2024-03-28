package epf.min2.projetmin_roommatehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClientViewHolder(view : View) : RecyclerView.ViewHolder(view)

class ClientAdapter(val users: List<User>) : RecyclerView.Adapter<ClientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.client_view,parent,false)
        return ClientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val user : User = users[position]
        val view : View = holder.itemView
        val clientNameTextView : TextView = view.findViewById<TextView>(R.id.client_textview)
        clientNameTextView.text = "${user.username} ${user.name}"
    }
}