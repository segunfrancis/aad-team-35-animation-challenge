package com.example.aad_team_35_animation_challenge.auth.customs

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class FlexibleLayout : FrameLayout {

    private var currentOrder: Int = 0

    constructor(context: Context) : super(context) {
        isChildrenDrawingOrderEnabled = true
    }

    constructor(context: Context,
                attrs: AttributeSet?) : super(context, attrs) {
        isChildrenDrawingOrderEnabled = true
    }

    constructor(context: Context,
                attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        isChildrenDrawingOrderEnabled = true
    }

    constructor(context: Context,
                attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        isChildrenDrawingOrderEnabled = true
    }

    fun setDrawOrder(order: Int) {
        currentOrder = order
        invalidate()
    }

    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
        return DRAW_ORDERS[currentOrder][i]
    }

    companion object {
        private val TAG = "OrderLayout"
        var ORDER_SIGN_UP_STATE = 0
        var ORDER_SIGN_IN_STATE = 1

        private val DRAW_ORDERS = arrayOf(intArrayOf(0, 1, 2), intArrayOf(2, 1, 0))
    }
}
