package com.pppp.mvicoreapp.main.view

import android.app.Activity
import com.google.android.material.snackbar.Snackbar

fun Activity.showError(message: String?) {
    message ?: return
    val rootView = this.window.decorView.rootView
    Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
}