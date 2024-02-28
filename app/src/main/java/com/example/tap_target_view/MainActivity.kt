package com.example.tap_target_view


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tap_target_view.databinding.ActivityMainBinding
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeMenuItem: MenuItem? = binding.bottomNavigationView.menu.findItem(R.id.nav_item_home)
        val searchMenuItem: MenuItem? = binding.bottomNavigationView.menu.findItem(R.id.nav_item_search)
        val profileMenuItem: MenuItem? = binding.bottomNavigationView.menu.findItem(R.id.nav_item_profile)

        // shared pref to show only the first time
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean("didShowTargetSequence", false)) {
            tapTargetOperations(homeMenuItem, searchMenuItem, profileMenuItem)
        }

        // bottom nav bar on click
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_home -> {
                    showToast("Home Clicked")
                    // Handle navigation or other actions for Home
                    true
                }
                R.id.nav_item_search -> {
                    showToast("Search Clicked")
                    // Handle navigation or other actions for Search
                    true
                }
                R.id.nav_item_profile -> {
                    showToast("Profile Clicked")
                    // Handle navigation or other actions for Profile
                    true
                }
                else -> false
            }
        }


    }

    private fun tapTargetOperations(
        homeMenuItem: MenuItem?,
        searchMenuItem: MenuItem?,
        profileMenuItem: MenuItem?
    ) {
        val targetSequence = mutableListOf<TapTarget>(
            createTarget(binding.btnFirst, "first", "description of the first button")!!,
            createTarget(binding.btnSecond, "second", "description of the second button")!!,
            createTarget(binding.btnThird, "third", "description of the third button")!!,
            createTarget(
                binding.bottomNavigationView.findViewById(homeMenuItem!!.itemId),
                "nav 1",
                "description of the forth button"
            )!!,
            createTarget(
                binding.bottomNavigationView.findViewById(searchMenuItem!!.itemId),
                "nav 2",
                "description of the fifth button",
                1
            )!!,
            createTarget(
                binding.bottomNavigationView.findViewById(profileMenuItem!!.itemId),
                "nav 3",
                "description of the sixth button"
            )!!,
        )

        showTargetSequence(
            this,
            *targetSequence.toTypedArray(),  // Spread the list to varargs
            onFinish = {
                // Save in shared preferences that the target sequence has been shown (use true)
                sharedPref.edit().putBoolean("didShowTargetSequence", false).apply()
            },
            onSeqStep = { tappedTarget ->
                if (tappedTarget.id() == 1
                ) {
                    // Cancel the sequence when reaching the searchMenuItem
                    onCancelSequence()
                }
            },
            onCancel = {}
        )
    }

    private fun onCancelSequence(){
        showToast("badri action")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}