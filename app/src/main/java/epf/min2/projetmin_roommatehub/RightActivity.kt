package epf.min2.projetmin_roommatehub

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import epf.min2.projetmin_roommatehub.databinding.ActivityMainBinding
import epf.min2.projetmin_roommatehub.databinding.ActivityRightBinding

class RightActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bindingRight: ActivityRightBinding
    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for ActivityRight
        bindingRight = ActivityRightBinding.inflate(layoutInflater)
        setContentView(bindingRight.root)

        // Inflate the layout for ActivityMain
        bindingMain = ActivityMainBinding.inflate(layoutInflater)

//        setSupportActionBar(bindingRight.appBarRight.toolbar)
        val drawerLayout: DrawerLayout = bindingRight.drawerLayout
        val navView: NavigationView = bindingRight.navView
        val bottomView: BottomNavigationView = bindingMain.navView

        // Ensure these IDs match your layout files
        val navController = findNavController(R.id.nav_host_fragment_content_right)
//        val navController2 = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        bottomView.setupWithNavController(navController2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.right, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_right)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
