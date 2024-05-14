package epf.min2.projetmin_roommatehub.utils

import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/rmh/user/")
    fun createUser(@Body newUser: NewUser): Call<Unit>
    @GET("/rmh/user/")
    fun getUsers(): Call<List<User>>

    @GET("/rmh/user/{id}/")
    fun getUser(@Path("id") userId: String): Call<User>

    @PATCH("rmh/user/{id}/")
    fun modifyUser(@Path("id") userId: Int, @Body user: User): Call<Unit>

    @POST("rmh/users/login/")
    fun logIn(@Body newUser: NewUser): Call<LogIn>


}
