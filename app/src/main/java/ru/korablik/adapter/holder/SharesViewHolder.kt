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

/**
 * Created by vkirillov on 22.11.2017.
 */
class SharesViewHolder(private val ui: SharesViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: SharesPayload) {
        (ui.viewPager.adapter as SharesAdapter).apply {
            icons = payload.imageResList
            notifyDataSetChanged()
            ui.pageIndicator.count = icons.size
            ui.pageIndicator.selection = ui.viewPager.currentItem
        }
    }
}

class SharesViewHolderUI : AnkoComponent<ViewGroup> {
    lateinit var root: LinearLayout
    lateinit var viewPager: ViewPager
    lateinit var pageIndicator: PageIndicatorView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = verticalLayout {
            lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            viewPager = autoScrollViewPager {
                adapter = SharesAdapter()
                scrollSpeed(1300)
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
                setViewPager(viewPager)
            }.lparams {
                topMargin = dip(8)
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
        return@with root
    }
}

class SharesAdapter : PagerAdapter() {

    var icons: List<Int> = listOf()

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = icons.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val ankoContext = AnkoContext.create(container.context, container)
        val view = ShareItemUI(icons[position]).createView(ankoContext)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    class ShareItemUI(private val imageRes: Int) : AnkoComponent<ViewGroup> {

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            imageView {
                Glide.with(this).load(imageRes).into(this)
            }
        }
    }

}