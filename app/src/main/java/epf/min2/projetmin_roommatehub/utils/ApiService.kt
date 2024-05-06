package epf.min2.projetmin_roommatehub.utils

import epf.min2.projetmin_roommatehub.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @POST("/rmh/user/")
    fun createUser(@Body user: User): Call<User>
    @GET("/rmh/user/")
    fun getUsers(): Call<List<User>>
}
