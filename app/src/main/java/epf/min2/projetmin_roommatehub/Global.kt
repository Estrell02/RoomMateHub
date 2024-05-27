package epf.min2.projetmin_roommatehub

object Global {
    lateinit var currentUser: Profil
    lateinit var accessToken: String
    lateinit var refreshToken: String
}
data class LogIn(
    var id: Int ,
    var access: String ,
    var refresh: String
)
