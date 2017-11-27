package ru.korablik.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

/**
 * Created by vkirillov on 27.11.2017.
 */
class AutoScrollViewPager : ViewPager {

    private val duration = 3000L

    private val mainThreadHandler = Handler(Looper.getMainLooper())

    private val nextPageRunnable: Runnable = Runnable {
        val adapter = adapter ?: return@Runnable
        if (adapter.count < 2) return@Runnable
        val current = if (currentItem == adapter.count - 1) 0 else currentItem + 1
        setCurrentItem(current, true)
        startCarousel()
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        adapter?.notifyDataSetChanged()
        startCarousel()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopCarousel()
    }

    private fun startCarousel() {
        val adapter = adapter ?: return
        if (adapter.count < 2) return

        mainThreadHandler.postDelayed(nextPageRunnable, duration)
    }

    private fun stopCarousel() {
        mainThreadHandler.removeCallbacksAndMessages(null)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        Log.d("pager", ev.action.toString())
        if (ev.action == MotionEvent.ACTION_CANCEL || ev.action == MotionEvent.ACTION_UP) {
            startCarousel()
            return super.onTouchEvent(ev)
        }
        stopCarousel()
        return super.onTouchEvent(ev)
    }
}