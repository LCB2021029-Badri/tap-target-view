package com.example.tap_target_view


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val rickTarget = Rect(0, 0, binding.btnThird.width, binding.btnThird.height)
//        val rickDrawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground) // Replace with your icon drawable

        TapTargetSequence(this)
            .targets(
                TapTarget.forView(findViewById<View>(R.id.btn_first), "Gonna")
                    .tintTarget(false),
                TapTarget.forView(findViewById<View>(R.id.btn_second), "You", "Up")
                    .tintTarget(false)
//                TapTarget.forBounds(rickTarget, "Down", ":^)")
//                    .cancelable(false)
//                    .icon(rickDrawable)
            )
            .listener(object : TapTargetSequence.Listener {
                override fun onSequenceFinish() {
                    Log.d("qq", "finish")
                }

                override fun onSequenceStep(lastTarget: TapTarget, targetClicked: Boolean) {
                    Log.d("qq", "seq step")
                }

                override fun onSequenceCanceled(lastTarget: TapTarget) {
                    Log.d("qq", "seq cancelled")
                    // Resume the sequence from the last target
                    resumeSequenceFromLastTarget(lastTarget)
                }
            })
            .start()


    }

    private fun resumeSequenceFromLastTarget(lastTarget: TapTarget) {
        // Create a new sequence starting from the last target
        val newSequence = TapTargetSequence(this)
            .targets(
                TapTarget.forView(findViewById<View>(R.id.btn_second), "You", "Up")
                    .tintTarget(false)
            )
            .listener(object : TapTargetSequence.Listener {
                override fun onSequenceFinish() {
                    Log.d("qq", "finish after resume")
                }

                override fun onSequenceStep(lastTarget: TapTarget, targetClicked: Boolean) {
                    Log.d("qq", "seq step after resume")
                }

                override fun onSequenceCanceled(lastTarget: TapTarget) {
                    Log.d("qq", "seq cancelled after resume")
                }
            })

        // Start the new sequence
        newSequence.start()
    }
}