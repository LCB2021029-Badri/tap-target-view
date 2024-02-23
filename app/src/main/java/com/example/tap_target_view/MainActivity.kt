package com.example.tap_target_view


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
import com.getkeepsafe.taptargetview.TapTargetView


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
//    lateinit var sharedPref: SharedPreferences
//    lateinit var prefEditor :  SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        sharedPref = getSharedPreferences("didShowPrompt", MODE_PRIVATE)
//        prefEditor =sharedPref.edit()

    showTargetSequence(this,
        createTarget(findViewById(R.id.btn_first), "hello", "bla bla")!!,
        createTarget(findViewById(R.id.btn_second), "hello", "bla bla")!!,
        )
    }



}