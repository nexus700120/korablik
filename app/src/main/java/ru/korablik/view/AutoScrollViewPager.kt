package ru.korablik.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
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
        val current = if (super.getCurrentItem() == adapter.count - 1) 0 else super.getCurrentItem() + 1
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
        stopCarousel()
        val adapter = adapter ?: return
        if (adapter.count < 2) return

        mainThreadHandler.postDelayed(nextPageRunnable, duration)
    }

    private fun stopCarousel() {
        mainThreadHandler.removeCallbacksAndMessages(null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val superResult = super.onTouchEvent(ev)
        if (ev.action == MotionEvent.ACTION_CANCEL || ev.action == MotionEvent.ACTION_UP) {
            startCarousel()
            return superResult
        }
        stopCarousel()
        return superResult
    }

    override fun setAdapter(adapter: PagerAdapter) {
        super.setAdapter(adapter)
        // offset first element so that we can scroll to the left
        currentItem = 0
    }

    override fun setCurrentItem(item: Int) {
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        var i = item
        if (adapter.count == 0) {
            super.setCurrentItem(i, smoothScroll)
            return
        }
        i %= adapter.count
        super.setCurrentItem(i, smoothScroll)
    }

    override fun getCurrentItem(): Int {
        if (adapter.count == 0) {
            return super.getCurrentItem()
        }
        val position = super.getCurrentItem()
        if (adapter is InfinitePagerAdapter) {
            val infAdapter = adapter as InfinitePagerAdapter
            // Return the actual item position in the data backing InfinitePagerAdapter
            return position % infAdapter.getRealCount()
        } else {
            return super.getCurrentItem()
        }
    }

    fun getRealCount(): Int {
        if (adapter.count == 0) {
            return 0
        }
        if (adapter is InfinitePagerAdapter) {
            val infAdapter = adapter as InfinitePagerAdapter
            return infAdapter.getRealCount()
        } else {
            return 0
        }
    }

    fun getRealPosition(position: Int): Int {
        return if (position == 0) 0 else position % getRealCount()
    }

    fun getInfiniteMiddlePosition(): Int {
        if (getRealCount() == 0) return 0
        if (adapter is InfinitePagerAdapter) {
            val infAdapter = adapter as InfinitePagerAdapter
            val middle = adapter.count / 2
            return middle + infAdapter.getRealCount() - middle % infAdapter.getRealCount()
        } else {
            return 0
        }
    }

    fun getAbsolutePosition(position: Int): Int {
        if (getRealCount() == 0) return 0
        if (adapter is InfinitePagerAdapter) {
            val infAdapter = adapter as InfinitePagerAdapter
            val middle = adapter.count / 2
            return middle + infAdapter.getRealCount() - middle % infAdapter.getRealCount() + position
        } else {
            return 0
        }
    }
}

interface InfinitePagerAdapter {
    fun getRealCount(): Int
}