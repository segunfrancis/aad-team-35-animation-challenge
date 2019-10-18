package com.example.aad_team_35_animation_challenge.auth.listeners

import android.opengl.Visibility

interface OnAuthListener {
    fun onAuth(visibility: Int)
    fun onFailure()
    fun onSuccess()
}