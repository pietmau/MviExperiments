package com.pppp.mvicoreapp.detail.view

import android.animation.ObjectAnimator
import android.view.View
import androidx.palette.graphics.Palette

const val ANIMATION_DURATION: Long = 250

fun DetailActivity.animateIn(view: View): ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationY", 0F).apply {
        duration = ANIMATION_DURATION
        start()
    }
}

fun DetailActivity.animateOut(decorView: View, view: View): ObjectAnimator {
    return ObjectAnimator.ofFloat(
        view,
        "translationY",
        (decorView.bottom.toFloat() - view.top)
    ).apply {
        duration = ANIMATION_DURATION
        start()
    }
}

fun Palette.Builder.dominantSwatch(listener: (Palette.Swatch) -> Unit) {
    this.generate { palette ->
        palette?.dominantSwatch?.let { swatch -> listener(swatch) }
    }
}

fun View.addOnGlobalLayoutListener(listener: () -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener(listener)
}