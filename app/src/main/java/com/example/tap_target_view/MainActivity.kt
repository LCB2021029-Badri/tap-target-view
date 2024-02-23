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

    showTargetSequence(
        createTarget(findViewById(R.id.btn_first), "hello", "bla bla")!!,
        createTarget(findViewById(R.id.btn_second), "hello", "bla bla")!!,
        )
    }

    private fun createTarget(view:View, title:String, desc:String, id:Int=-1): TapTarget? =
        TapTarget.forView(view,title,desc)
            .tintTarget(false)
            .id(id)

    private fun showTargetSequence(
        vararg tapTarget: TapTarget,
        onFinish : () -> Unit = {},
        onCancel : () -> Unit = {}
    ){
        TapTargetSequence(this)
            .targets(tapTarget.toList()
                ).listener(object :TapTargetSequence.Listener{
                override fun onSequenceFinish() {
                    Log.d("badri", "hello")
                    onFinish()
                }

                override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                    Log.d("badri", "hello")
                }

                override fun onSequenceCanceled(lastTarget: TapTarget?) {
                    Log.d("badri", "hello")
                    onCancel()
                }

            })
            .start()
    }


}