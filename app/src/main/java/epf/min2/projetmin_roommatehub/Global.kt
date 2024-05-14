package epf.min2.projetmin_roommatehub

object Global {
    var currentUser: User = User(0,"init_firstname","init_username","init_lastname","init_email")
    var accessToken: String = ""
    var refreshToken: String = ""
}
data class LogIn(
    var id: Int ,
    var access: String ,
    var refresh: String
)
