package com.example.pregnantpro.ginecologa

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import com.example.pregnantpro.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

open class BaseGinecologa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_ginecologo, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.home -> {
                startActivity(Intent (this, HomeGinecologaActivity::class.java))
                true
            }
            R.id.logout1 -> {
                Firebase.auth.signOut()
                startActivity(Intent (this, HomeGinecologaActivity::class.java))

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}