package epf.min2.projetmin_roommatehub

import android.net.Uri
import java.util.Date

data class Annonce(
    val id:Int,
    val title: String,
    val description: String,
    val price: Double,
    val location: String,
    val photo: Uri,
    val created_at: Date,
    val owner: User
)