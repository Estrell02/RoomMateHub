package epf.min2.projetmin_roommatehub.utils

import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("/rmh/user/")
    suspend fun createUser(@Body newUser: NewUser): Response<Unit>
    @GET("/rmh/user/")
    suspend fun getUsers(): Response<List<User>>

    @GET("/rmh/user/{id}/")
    suspend fun getUser(@Path("id") userId: String): Response<User>

    @PATCH("rmh/user/{id}/")
    suspend fun modifyUser(@Path("id") userId: Int, @Body user: User):Response<Unit>

    @POST("rmh/users/login/")
    suspend fun logIn(@Body newUser: NewUser): Response<LogIn>

    //      Profil            ////////////////////////////////////////////////////////////////////

    @GET("/rmh/profile/")
    suspend fun getProfils(): Response<List<Profil>>

    @GET("/rmh/profile/{id}/")
    suspend fun getProfil(@Path("id") profilId: String): Response<Profil>


    //      Annonce           /////////////////////////////////////////////////////////////////////
    @GET("/rmh/announce/")
    suspend fun getAnnonces(): Response<List<Annonce>>

    @GET("/rmh/announce/{id}/")
    suspend fun getAnnonce(@Path("id") annonceId: String): Response<Annonce>

    @Multipart
    @POST("/announce/housing/")
    suspend fun createAnnonce(@Part("title") title: RequestBody, @Part("description") description: RequestBody, @Part("price") price: RequestBody, @Part("location") location: RequestBody, @Header("Token") token: String, @Part photo: MultipartBody.Part): Response<Unit>
}
