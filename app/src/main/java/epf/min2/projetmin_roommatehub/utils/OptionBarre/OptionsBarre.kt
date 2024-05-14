package epf.min2.projetmin_roommatehub.utils.OptionBarre

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import epf.min2.projetmin_roommatehub.R
import epf.min2.projetmin_roommatehub.home.HomeActivity

class OptionsBarre(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.options_barre, this, true)

        findViewById<TextView>(R.id.btnHome).setOnClickListener {
            Toast.makeText(context, "Retour Home", Toast.LENGTH_LONG).show()
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
        findViewById<TextView>(R.id.btnModifier).setOnClickListener {
            Toast.makeText(context, "Modification Utilisateur", Toast.LENGTH_LONG).show()
            val intent = Intent(context, ModifyUserActivity::class.java)
            context.startActivity(intent)
        }
        findViewById<TextView>(R.id.btnDeconnexion).setOnClickListener {
        }
    }
}

