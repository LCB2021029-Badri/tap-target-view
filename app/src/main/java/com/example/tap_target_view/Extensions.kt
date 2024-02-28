package com.example.tap_target_view

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence

fun createTarget(view: View, title:String, desc:String, id:Int=-1): TapTarget? =
    TapTarget.forView(view,title,desc)
        .outerCircleColor(R.color.c2)
        .tintTarget(false)
        .cancelable(false)
        .id(id)

fun showTargetSequence(
    activity: Activity,
    vararg tapTarget: TapTarget,
    onFinish : () -> Unit = {},
    onSeqStep : (TapTarget) -> Unit = {},
    onCancel : () -> Unit = {}
){
    TapTargetSequence(activity)
        .targets(tapTarget.toList()
        ).listener(object : TapTargetSequence.Listener{
            override fun onSequenceFinish() {
                Log.d("badri", "hello")
                onFinish()
            }

            override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                Log.d("badri", "hello")
                lastTarget?.let { onSeqStep(it) }
            }

            override fun onSequenceCanceled(lastTarget: TapTarget?) {
                Log.d("badri", "hello")
                onCancel()
            }

        })
        .start()
}