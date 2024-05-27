package epf.min2.projetmin_roommatehub

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class NewUser(
    val first_name: String,
    val username: String,
    val last_name: String,
    val email: String,
    val password: String
)


data class User(
    val id : Int,
    val first_name: String,
    val username: String,
    val last_name: String,
    val email: String
)