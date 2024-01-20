package com.example.gardeningjournalapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gardeningjournalapp.databinding.ActivityMainBinding
import com.example.gardeningjournalapp.model.GardenLogViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mnavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        mnavController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,mnavController)

        // Sample plants
        val viewModel = GardenLogViewModel(application)
        viewModel.initializeSampleDataIfEmpty()
    }

    override fun onSupportNavigateUp(): Boolean {
        return mnavController.navigateUp()
    }
}
