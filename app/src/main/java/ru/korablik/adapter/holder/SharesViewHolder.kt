package ru.korablik.adapter.holder

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.anko.autoScrollViewPager
import ru.korablik.anko.pagerIndicator
import ru.korablik.color
import ru.korablik.domain.SharesPayload
import ru.korablik.ext.scrollSpeed
import ru.korablik.view.AutoScrollViewPager
import ru.korablik.view.InfinitePagerAdapter

/**
 * Created by vkirillov on 22.11.2017.
 */
class SharesViewHolder(private val ui: SharesViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: SharesPayload) {
        (ui.viewPager.adapter as SharesAdapter).apply {
            icons = payload.imageResList
            notifyDataSetChanged()

            ui.viewPager.setCurrentItem(ui.viewPager.getInfiniteMiddlePosition(), true)
            ui.pageIndicator.count = icons.size
            ui.pageIndicator.selection = ui.viewPager.getRealPosition(ui.viewPager.currentItem)
        }
    }
}

class SharesViewHolderUI : AnkoComponent<ViewGroup> {
    lateinit var root: LinearLayout
    lateinit var viewPager: AutoScrollViewPager
    lateinit var pageIndicator: PageIndicatorView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = verticalLayout {
            lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            viewPager = autoScrollViewPager {
                adapter = SharesAdapter()
                scrollSpeed(1000)
            }.lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = dip(136)
            }
            pageIndicator = pagerIndicator {
                unselectedColor = ctx.color(R.color.colorUnselectedIndicator)
                selectedColor = ctx.color(R.color.colorSelectedIndicator)
                radius = 3
                padding = 6
                setAnimationType(AnimationType.SLIDE)
            }.lparams {
                topMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    pageIndicator.onPageScrolled(viewPager.getRealPosition(position), positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    pageIndicator.onPageSelected(viewPager.getRealPosition(position))
                }
            })
        }
        return@with root
    }
}

class SharesAdapter : PagerAdapter(), InfinitePagerAdapter {

    var icons: List<Int> = listOf()

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = if (icons.isEmpty()) 0 else 10000

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageRes = icons[position.rem(getRealCount())]
        val ankoContext = AnkoContext.create(container.context, container)
        val view = ShareItemUI(imageRes).createView(ankoContext)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

    override fun getRealCount(): Int = icons.size

    class ShareItemUI(private val imageRes: Int) : AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            imageView {
                Glide.with(this).load(imageRes).into(this)
            }
        }
    }

}