package com.example.aad_team_35_animation_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import com.example.aad_team_35_animation_challenge.auth.listeners.OnLogoutListener

/** This activity only holds a menu with logout functionality
 * is should be replaced with the appropriate start/main activity
 * or if logout function is to be performed from button click
 * the necessary code should be copied and transferred to
 * button click listener
 * */

class ActivityHavingLogoutMenu : AppCompatActivity(), OnLogoutListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_having_logout_menu)
    }

    override fun logout() {
        //remove all sign in data from preferences to logout user
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()

        editor.putString(getString(R.string.registered_address),getString(R.string.empty_string))
        editor.putString(getString(R.string.registered_name), getString(R.string.empty_string))
        editor.putString(getString(R.string.registered_password), getString(R.string.empty_string))
        editor.apply()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }
}
