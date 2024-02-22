package com.example.tap_target_view


import android.graphics.Rect
import android.os.Bundle
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

        val rickTarget = Rect(0, 0, binding.btnThird.width, binding.btnThird.height)
        val rickDrawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground) // Replace with your icon drawable

        TapTargetSequence(this)
            .targets(
                TapTarget.forView(findViewById<View>(R.id.btn_first), "Gonna")
                    .tintTarget(false)
                ,
                TapTarget.forView(findViewById<View>(R.id.btn_second), "You", "Up")
                    .dimColor(R.color.c1)
                    .outerCircleColor(R.color.c2)
                    .targetCircleColor(R.color.c3)
                    .textColor(R.color.c4),
                TapTarget.forBounds(rickTarget, "Down", ":^)")
                    .cancelable(false)
                    .icon(rickDrawable)
            )
            .listener(object : TapTargetSequence.Listener {
                // This listener will tell us when interesting(tm) events happen in regards
                // to the sequence
                override fun onSequenceFinish() {
                    // Yay
                }

                override fun onSequenceStep(lastTarget: TapTarget, targetClicked: Boolean) {
                    // Perform action for the current target
                }

                override fun onSequenceCanceled(lastTarget: TapTarget) {
                    // Boo
                }
            })
            .start()

//        TapTargetSequence(this).target(
//            TapTarget.forView(findViewById(R.id.btn_first),"hello")
//        ).start()


    }
}