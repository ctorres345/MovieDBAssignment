package com.backbase.assignment.presentation.adapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpacingDecorator(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        if (parent.getChildAdapterPosition(view) != itemCount - 1) {
            outRect.bottom = spacing
        }
        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            outRect.bottom = spacing
        }
    }
}