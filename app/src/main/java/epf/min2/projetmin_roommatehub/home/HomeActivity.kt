package epf.min2.projetmin_roommatehub.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.API
import epf.min2.projetmin_roommatehub.utils.ClientAdapter


class HomeActivity : AppCompatActivity(), API.ApiListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)


        val apiClient = API(this)
        val url = "http://54.38.241.241:8000/rmh/user"//"https://jsonplaceholder.typicode.com/users"
        apiClient.fetchData(url)

    }

    override fun onSuccess(users: List<User>) {
        runOnUiThread {
            val recyclerView = findViewById<RecyclerView>(R.id.home_recyclerview)

            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = ClientAdapter(users)
        }
    }

    override fun onFailure(error: String) {
        println("Erreur: $error")
    }

}