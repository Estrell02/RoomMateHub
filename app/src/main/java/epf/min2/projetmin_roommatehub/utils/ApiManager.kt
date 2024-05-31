package epf.min2.projetmin_roommatehub.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.LogIn
import epf.min2.projetmin_roommatehub.NewUser
import epf.min2.projetmin_roommatehub.Profil
import epf.min2.projetmin_roommatehub.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
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


    suspend fun createAnnonce(context:Context, newAnnonce: Annonce):Response<Unit>{
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(newAnnonce.photo)

        val fileName = newAnnonce.photo.lastPathSegment ?: "image"
        val extension = fileName.substringAfterLast('.', "jpg")

        val tempFile = File.createTempFile("image", ".$extension", context.cacheDir).apply {
            outputStream().use { inputStream?.copyTo(it) }
        }

        val requestFile = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("photo", tempFile.name, requestFile)

        val title = newAnnonce.title.toRequestBody("text/plain".toMediaTypeOrNull())
        val description = newAnnonce.description.toRequestBody("text/plain".toMediaTypeOrNull())
        val price = newAnnonce.price.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val location = newAnnonce.location.toRequestBody("text/plain".toMediaTypeOrNull())

        inputStream?.close()
        return apiService.createAnnonce(title,description,price,location,Global.accessToken,body)
    }
}
