package ru.korablik.utils

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * Created by vkirillov on 27.11.2017.
 */
class FixedSpeedScroller : Scroller {

    var fixedDuration = 5000

    constructor(context: Context?) : super(context)
    constructor(context: Context?, interpolator: Interpolator?) : super(context, interpolator)
    constructor(context: Context?, interpolator: Interpolator?, flywheel: Boolean) : super(context, interpolator, flywheel)

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, fixedDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, fixedDuration)
    }
}