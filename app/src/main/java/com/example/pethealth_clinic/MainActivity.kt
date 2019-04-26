package com.example.pethealth_clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }
}
