package epf.min2.projetmin_roommatehub

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import epf.min2.projetmin_roommatehub.ui.List_Annonces.List_annonce_Fragment
//import epf.min2.projetmin_roommatehub.ui.List_Annonces.List_annonce_Fragment
import epf.min2.projetmin_roommatehub.ui.dashboard.DashboardFragment
import epf.min2.projetmin_roommatehub.ui.gallery.GalleryFragment
import epf.min2.projetmin_roommatehub.ui.home.HomeFragment
import epf.min2.projetmin_roommatehub.ui.notifications.NotificationsFragment
import epf.min2.projetmin_roommatehub.ui.slideshow.SlideshowFragment

class MainActivity : AppCompatActivity()  {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomAppBar: BottomAppBar

    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var bottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        bottomAppBar = findViewById(R.id.bottomAppBar)

        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.nav_view_drawer)
        bottomNavView = findViewById(R.id.nav_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.nav_gallery -> {
                    replaceFragment(GalleryFragment())
                }
                R.id.nav_slideshow -> {
                    replaceFragment(SlideshowFragment())
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(navView)
            true
        }

        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.navigation_liste_annonce -> {
                    replaceFragment(List_annonce_Fragment())
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(DashboardFragment())
                }
                R.id.navigation_notifications -> {
                    replaceFragment(NotificationsFragment())
                }
            }
            true
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.right, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}