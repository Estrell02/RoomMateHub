package epf.min2.projetmin_roommatehub.utils

import epf.min2.projetmin_roommatehub.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager() {

    interface ApiListener<T> {
        fun onSuccess(data: T)
        fun onFailure(error: String)
    }


    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.38.241.241:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getUsers(url: String, listener: ApiListener<List<User>>) {
        val call = apiService.getUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    users?.let { listener.onSuccess(it) } ?: listener.onFailure("Réponse vide")
                } else {
                    listener.onFailure("Erreur: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listener.onFailure(t.message ?: "Erreur inconnue")
            }
        })
    }

}
