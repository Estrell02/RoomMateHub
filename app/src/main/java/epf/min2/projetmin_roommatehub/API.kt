package epf.min2.projetmin_roommatehub

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class API(private val listener: ApiListener) {

    interface ApiListener {
        fun onSuccess(users: List<User>)
        fun onFailure(error: String)
    }

    fun fetchData(url: String) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                listener.onFailure(e.message ?: "Erreur inconnue")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonData = response.body?.string()
                    val gson = Gson()
                    val users: Array<User> = gson.fromJson(jsonData, Array<User>::class.java)
                    listener.onSuccess(users.toList())
                } else {
                    listener.onFailure("Erreur: ${response.code} ${response.message}")
                }
            }
        })
    }
}
