package be.hogent.vic

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import be.hogent.vic.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bar met "VIC" titel verbergen
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // var navView : NavigationView = findViewById(R.id.bottom)

        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        //appBarConfiguration = AppBarConfiguration(navController.graph)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        //NavigationUI.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it,findNavController(R.id.navHostFragment))
            findNavController(R.id.navHostFragment).popBackStack(it.itemId, inclusive = false)
            true
        }
    }




}