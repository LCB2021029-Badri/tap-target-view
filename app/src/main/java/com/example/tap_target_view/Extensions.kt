package com.example.tap_target_view

import android.app.Activity
import android.util.Log
import android.view.View
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence

fun createTarget(view: View, title:String, desc:String, id:Int=-1): TapTarget? =
    TapTarget.forView(view,title,desc)
        .tintTarget(false)
        .id(id)

fun showTargetSequence(
    activity: Activity,
    vararg tapTarget: TapTarget,
    onFinish : () -> Unit = {},
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
            }

            override fun onSequenceCanceled(lastTarget: TapTarget?) {
                Log.d("badri", "hello")
                onCancel()
            }

        })
        .start()
}