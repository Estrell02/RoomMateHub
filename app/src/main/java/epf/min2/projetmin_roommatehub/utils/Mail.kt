package epf.min2.projetmin_roommatehub.utils

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun envoyerEmail(destinataire: String, sujet: String, corps: String) {
    val proprietes = Properties()
    proprietes["mail.smtp.auth"] = "true"
    proprietes["mail.smtp.starttls.enable"] = "true"
    proprietes["mail.smtp.host"] = "smtp.office365.com" // Serveur SMTP Outlook
    proprietes["mail.smtp.port"] = "587" // Port SMTP

    val session = Session.getInstance(proprietes, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication("thomas.beurdouche@epfedu.fr", "040302t%") // Votre adresse e-mail Outlook et mot de passe
        }
    })

    try {
        val message = MimeMessage(session)
        message.setFrom(InternetAddress("thomas.beurdouche@epfedu.fr")) // Votre adresse e-mail Outlook
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire))
        message.subject = sujet
        message.setText(corps)

        Transport.send(message)

        println("E-mail envoyé avec succès à $destinataire")
    } catch (e: MessagingException) {
        println("Erreur lors de l'envoi de l'e-mail: ${e.message}")
    }
}

fun main() {
    val destinataire = "thomas.beurdouche@epfedu.fr" // Adresse e-mail du destinataire
    val sujet = "Test d'envoi d'e-mail depuis une application Kotlin avec Outlook"
    val corps = "Ceci est un test d'envoi d'e-mail depuis une application Kotlin avec Outlook."

    envoyerEmail(destinataire, sujet, corps)
}
