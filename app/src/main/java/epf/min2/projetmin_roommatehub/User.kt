package epf.min2.projetmin_roommatehub


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