package epf.min2.projetmin_roommatehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.Annonce
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson

class DetailAnnonceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_annonce_layout)

        val annonceJson = intent.getStringExtra("annonce")
        val annonce = Gson().fromJson(annonceJson, Annonce::class.java)

        val annonceTitleTextView: TextView = findViewById(R.id.annonce_detail_title_textview)
        val annonceTypeChambreTextView: TextView = findViewById(R.id.annonce_detail_type_chambre_textview)
        val annonceLocationTextView: TextView = findViewById(R.id.annonce_detail_location_textview)
        val annoncePriceTextView: TextView = findViewById(R.id.annonce_detail_price_textview)
        val annonceImageView: ImageView = findViewById(R.id.annonce_detail_image_imageView)

        annonce?.let {
            annonceImageView.setImageResource(R.drawable.img)
            annonceLocationTextView.text = "${it.location}"
            annoncePriceTextView.text = "Prix: ${it.price}"
            annonceTitleTextView.text = "${it.title}"
        }
    }
}
