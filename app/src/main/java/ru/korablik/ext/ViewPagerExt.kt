package ru.korablik.ext

import android.support.v4.view.ViewPager
import android.view.animation.DecelerateInterpolator
import ru.korablik.utils.FixedSpeedScroller
import java.lang.reflect.Field


/**
 * Created by vkirillov on 27.11.2017.
 */

inline fun ViewPager.scrollSpeed(time: Int) {
    try {
        val mScroller: Field = ViewPager::class.java.getDeclaredField("mScroller")
        mScroller.isAccessible = true
        val scroller = FixedSpeedScroller(context)
        scroller.fixedDuration = time
        mScroller.set(this, scroller)
    } catch (e: Exception) {}

}