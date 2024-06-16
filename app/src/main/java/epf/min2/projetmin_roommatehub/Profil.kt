package epf.min2.projetmin_roommatehub

data class Profil(
    val user: User,
    //"picture": null,
//    val hobbies: List<String>,
    val hobbies:String,
    val vegan: Boolean,
    val filiere: String
)