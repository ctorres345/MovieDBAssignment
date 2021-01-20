package com.backbase.assignment.presentation.ext

import android.view.View
import com.backbase.assignment.util.DebounceClickListener

internal fun View.makeVisible() {
    visibility = View.VISIBLE
}

internal fun View.isVisible() = visibility == View.VISIBLE

internal fun View.makeVisibleElseGone(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

internal fun View.makeVisibleElseInvisible(makeVisible: Boolean) {
    visibility = if (makeVisible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

internal fun View.makeGone() {
    visibility = View.GONE
}

internal fun View.isGone() = visibility == View.GONE

internal fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

internal fun View.isInvisible() = visibility == View.INVISIBLE

fun View.setDebounceOnClickListener(doClick: (View) -> Unit) = setOnClickListener(
    DebounceClickListener(doClick = doClick)
)