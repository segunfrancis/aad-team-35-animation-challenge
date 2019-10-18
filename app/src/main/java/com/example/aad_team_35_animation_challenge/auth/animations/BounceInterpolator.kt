package com.example.aad_team_35_animation_challenge.auth.animations

import android.view.animation.Interpolator

class BounceInterpolator(private val amplitude: Double,
                         private val frequency: Double) : Interpolator {

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.pow(Math.E, -time / amplitude) * Math.cos(frequency * time) + 1).toFloat()
    }
}
