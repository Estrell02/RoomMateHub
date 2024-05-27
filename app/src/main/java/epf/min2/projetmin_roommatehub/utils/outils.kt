package epf.min2.projetmin_roommatehub.utils

import android.view.View

object outils {
    fun clickAilleurs(listeObject : Array<View>){
        for(objet in listeObject){
            objet.isEnabled = false
            objet.isEnabled = true
        }
    }
}