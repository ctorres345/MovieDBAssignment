package com.backbase.assignment.presentation.ext

import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.makeVisibleElseGone(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.makeVisibleElseInvisible(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.isGone() = visibility == View.GONE

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}