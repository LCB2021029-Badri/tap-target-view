package com.example.tap_target_view


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
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

        // shared pref to show only the first time
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean("didShowTargetSequence", false)) {
            showTargetSequence(
                this,
                createTarget(binding.btnFirst, "asf", "afaf")!!,
                createTarget(binding.btnSecond, "asf", "afaf")!!,
                createTarget(binding.btnThird, "asf", "afaf")!!,
                onFinish = {
                    // Save in shared preferences that the target sequence has been shown
                    sharedPref.edit().putBoolean("didShowTargetSequence", true).apply()
                },
                onCancel = {}
            )
        }


    }

}