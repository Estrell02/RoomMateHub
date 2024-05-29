package epf.min2.projetmin_roommatehub.utils

import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.core.net.toFile
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.InputStream

class ApiManager() {


    private val apiService: ApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.38.241.241:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun logIn(newUser: NewUser) : Response<LogIn>{
        return apiService.logIn(newUser)
    }

    suspend fun modifyUser(user:User):Response<Unit>{
        return apiService.modifyUser(user.id,user)
    }

    suspend fun createUser(newUser: NewUser):Response<Unit>{
        return apiService.createUser(newUser)
    }

    suspend fun getUsers():Response<List<User>> {
        return apiService.getUsers()
    }

    suspend fun getUser(idUser : String):Response<User>{
        return apiService.getUser(idUser)
    }

    //       Profil         ///////////////////////////////////////////////////////////////////////
    suspend fun getProfil(profilId: String) : Response<Profil>{
        return apiService.getProfil(profilId)
    }

    suspend fun getProfils():Response<List<Profil>> {
        return apiService.getProfils()
    }


    //       Annonce        ///////////////////////////////////////////////////////////////////////
    suspend fun getAnnonces():Response<List<Annonce>>{
        return apiService.getAnnonces()
    }

    suspend fun getAnnonce(idAnnonce:String):Response<Annonce>{
        return apiService.getAnnonce(idAnnonce)
    }

    suspend fun createAnnonce(newAnnonce: Annonce):Response<Unit>{

        val file = newAnnonce.photo.toFile()
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("photo", file.name, requestBody)
        return apiService.createAnnonce(newAnnonce,Global.accessToken,multipartBody)
    }
}
