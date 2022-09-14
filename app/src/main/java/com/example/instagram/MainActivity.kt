package com.example.instagram

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagram.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        //val drawerLayout: DrawerLayout = binding.drawerLayout

        /*appBarConfig = AppBarConfiguration(
            setOf(
                R.id.nav_login
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfig)*/
        // cor status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.statusBarColor = getColor(R.color.white)
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""

        configNavigation()
    }

    private fun configNavigation() {
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //title = destination.label
            //title = ""
            binding.appBarMain.toolbar.title = title

            // o método abaixo serve para incluir ou remover a navegação segundo o fragment carregado
            when (destination.id) {
                R.id.nav_login -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
                }
                R.id.nav_register_email -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
                }
                R.id.nav_register_name_password -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
                }
                R.id.nav_register_welcome -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
                }
                R.id.nav_register_upload_photo -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
                }
                R.id.nav_profile -> {
                    supportActionBar?.show()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.VISIBLE
                }
                R.id.nav_camera -> {
                    supportActionBar?.show()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.VISIBLE
                }
                R.id.nav_image_cropper -> {
                    supportActionBar?.hide()
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    binding.appBarMain.contentMain.bottomNav.visibility = View.GONE
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