package ru.korablik.adapter.holder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import org.jetbrains.anko.*

/**
 * Created by vkirillov on 22.11.2017.
 */
class ProductCarouselViewHolder(ui: ProductCarouselUI): RecyclerView.ViewHolder(ui.root) {


}

class ProductCarouselUI : AnkoComponent<ViewGroup> {

    lateinit var root: View

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = frameLayout {
            lparams(FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dip(100)))
            backgroundColor = Color.CYAN
        }
        return@with root
    }

}