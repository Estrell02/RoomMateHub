package epf.min2.projetmin_roommatehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projetmin_roommatehub.utils.AnnonceAdapter
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.ClientAdapter
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class UserListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_layout)

        val apiManager = ApiManager()

        runBlocking {
            val response: Response<List<User>> = apiManager.getUsers()
            if (response.isSuccessful){
                val users : List<User> = response.body()!!
                val recyclerView = findViewById<RecyclerView>(R.id.userListRecyclerview)
                recyclerView.layoutManager = LinearLayoutManager(this@UserListActivity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = ClientAdapter(users)
            } else {
                println(response.errorBody())
            }

        }


    }
}