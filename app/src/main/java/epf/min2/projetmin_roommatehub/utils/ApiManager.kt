package epf.min2.projetmin_roommatehub.utils

import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
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

    fun getUsers(listener: ApiListener<List<User>>) {
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

    fun getUser(idUser:String,listener: ApiListener<User>) {
        val call = apiService.getUser(idUser)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let { listener.onSuccess(it) } ?: listener.onFailure("Réponse vide")
                } else {
                    listener.onFailure("Erreur: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                listener.onFailure(t.message ?: "Erreur inconnue")
            }
        })
    }

    fun createUser(newUser: NewUser, listener: ApiListener<Unit>) {
        val call = apiService.createUser(newUser)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (!response.isSuccessful) {
                    listener.onFailure("Erreur: ${response.code()} ${response.message()}")
                } else {
                    listener.onSuccess(Unit)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                listener.onFailure(t.message ?: "Erreur inconnue")
            }
        })
    }

    fun modifyUser(user: User, listener: ApiListener<Unit>) {
        val call = apiService.modifyUser(user.id,user)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (!response.isSuccessful) {
                    listener.onFailure("Erreur: ${response.code()} ${response.message()}")
                } else {
                    listener.onSuccess(Unit)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                listener.onFailure(t.message ?: "Erreur inconnue")
            }
        })
    }


    fun logIn(newUser: NewUser, listener: ApiListener<LogIn>) {
        val call = apiService.logIn(newUser)

        call.enqueue(object : Callback<LogIn> {
            override fun onResponse(call: Call<LogIn>, response: Response<LogIn>) {
                if (response.isSuccessful) {
                    val logIn = response.body()
                    logIn?.let { listener.onSuccess(it) } ?: listener.onFailure("Réponse vide")
                } else {
                    listener.onFailure("Erreur: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LogIn>, t: Throwable) {
                listener.onFailure(t.message ?: "Erreur inconnue")
            }
        })
    }
}
