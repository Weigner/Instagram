package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagram.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        //val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        /*appBarConfig = AppBarConfiguration(
            setOf(
                R.id.nav_login
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfig)*/
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
            binding.appBarMain.toolbar.title = title

            // o método abaixo serve para incluir ou remover a navegação segundo o fragment carregado
            when (destination.id) {
                R.id.nav_login -> {
                    supportActionBar?.hide()
                    binding.appBarMain.toolbar.navigationIcon = null
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    supportActionBar?.show()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }
    }

    /* override fun onSupportNavigateUp(): Boolean {
         val navController = findNavController(R.id.nav_host_fragment_content_main)
         return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
     }*/
}