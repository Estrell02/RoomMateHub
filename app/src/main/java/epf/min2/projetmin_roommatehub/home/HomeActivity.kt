package epf.min2.projetmin_roommatehub.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.User
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ClientAdapter


class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        val apiManager = ApiManager()
        apiManager.getUsers("http://54.38.241.241:8000/rmh/user", object : ApiManager.ApiListener<List<User>> {
            override fun onSuccess(data: List<User>) {
                runOnUiThread {
                    val recyclerView = findViewById<RecyclerView>(R.id.home_recyclerview)

                    recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = ClientAdapter(data)
                }
            }

            override fun onFailure(error: String) {
                println("Erreur: $error")
            }
        })

    }

}