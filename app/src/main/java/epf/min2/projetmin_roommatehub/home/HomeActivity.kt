package epf.min2.projetmin_roommatehub.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import epf.min2.projetmin_roommatehub.AnnonceList.AnnonceListActivity
import epf.min2.projetmin_roommatehub.ProfilList.ProfilListActivity
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.UserListActivity


class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)

        val buttonListProfil = findViewById<Button>(R.id.homeProfilListButton)
        val buttonListAnnonce = findViewById<Button>(R.id.homeAnnonceListButton)
        val buttonListUser = findViewById<Button>(R.id.homeUserListButton)

        buttonListProfil.setOnClickListener{
            val intent = Intent(this@HomeActivity, ProfilListActivity::class.java)
            startActivity(intent)
        }
        buttonListUser.setOnClickListener{
            val intent = Intent(this@HomeActivity, UserListActivity::class.java)
            startActivity(intent)
        }
        buttonListAnnonce.setOnClickListener{
            val intent = Intent(this@HomeActivity, AnnonceListActivity::class.java)
            startActivity(intent)
        }

    }

}