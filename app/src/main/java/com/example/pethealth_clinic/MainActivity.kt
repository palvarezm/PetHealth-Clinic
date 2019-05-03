package com.example.pethealth_clinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.navigation_appointments ->{
                println("Mis citas")
                replaceFragment(AppointmentsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_myProfile ->{
                println("Mi perfil")
                replaceFragment(myProfileFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chat ->{
                println("Chat")
                replaceFragment(ChatFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        /*val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,host)
            .setPrimaryNavigationFragment(host)
            .commit()*/
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
