package imageapp.com.mobiosafe

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // Bottom Navigation
        navController = findNavController(R.id.hostFragment)
        bottom_navigation.setupWithNavController(navController)

        // Navigation UP button
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        // Drawer Navigation
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        NavigationUI.setupWithNavController(navigationView, navController)

        //trail
        //var headerView:View = LayoutInflater.from(this).inflate(R.layout.drawer_header,null)
        //navigationView.addHeaderView(headerView)
        //navigationView.getHeaderView(0).setVisibility(View.GONE)
    }

    fun startService(v: View?) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        startService(serviceIntent)
    }

    fun stopService(v: View?) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}