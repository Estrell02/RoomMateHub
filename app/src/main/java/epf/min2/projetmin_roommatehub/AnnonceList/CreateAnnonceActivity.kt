package epf.min2.projetmin_roommatehub.AnnonceList

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import epf.min2.projetmin_roommatehub.Annonce
import epf.min2.projetmin_roommatehub.Global
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.utils.ApiManager
import epf.min2.projetmin_roommatehub.utils.outils
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.util.Date

class CreateAnnonceActivity : AppCompatActivity() {

    var selectedImageUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_annonce_layout)

        val editTextTitreAnnonce = findViewById<EditText>(R.id.editTextTitreAnnonce)
        val editTextDescriptionAnnonce = findViewById<EditText>(R.id.editTextDescriptionAnnonce)
        val editTextPrixAnnonce = findViewById<EditText>(R.id.editTextPriceAnnonce)
        val editTextLocationAnnonce = findViewById<EditText>(R.id.editTextLocationAnnonce)

        val imageViewImageAnnonce = findViewById<ImageView>(R.id.imageViewAnnonce)

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmitCreateAnnonce)
        val buttonImageAnnonce =findViewById<Button>(R.id.buttonImageCreateAnnonce)


        val createAnnonceLayout = findViewById<ScrollView>(R.id.create_annonce_layout)

        val apiManager = ApiManager()

        buttonSubmit.setOnClickListener {
            val titre = editTextTitreAnnonce.text.toString()
            val description = editTextDescriptionAnnonce.text.toString()
            val prix = editTextPrixAnnonce.text.toString()
            val location = editTextLocationAnnonce.text.toString()

            if (titre.isNotEmpty() && description.isNotEmpty() && prix.isNotEmpty() && location.isNotEmpty() && selectedImageUri!==null) {

                val newAnnonce = Annonce(0,titre, description,prix.toDouble(),location, selectedImageUri!!,Date(),Global.currentUser.user)

                runBlocking {
                    var response: Response<Unit> = apiManager.createAnnonce(newAnnonce)
                    if (response.isSuccessful){
                        Toast.makeText(this@CreateAnnonceActivity, "Annonce créée", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@CreateAnnonceActivity, AnnonceListActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        println(response.errorBody())
                        Toast.makeText(this@CreateAnnonceActivity, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }

        createAnnonceLayout.setOnClickListener {
            val viewArray: Array<View> = arrayOf(editTextTitreAnnonce, editTextDescriptionAnnonce, editTextPrixAnnonce,editTextLocationAnnonce)
            outils.clickAilleurs(viewArray)
        }

        buttonImageAnnonce.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Veuillez autoriser l'accès pour pouvoir choisir une image", Toast.LENGTH_LONG).show()
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 101)
            } else {
                selectImageFromGallery()
            }
        }
    }


    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data.let {uri ->
                selectedImageUri = uri
                val imageViewImageAnnonce = findViewById<ImageView>(R.id.imageViewAnnonce)
                imageViewImageAnnonce.setImageURI(uri)
            }
        }
    }


}
