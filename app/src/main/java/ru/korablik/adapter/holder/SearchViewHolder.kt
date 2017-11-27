package ru.korablik.adapter.holder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import ru.korablik.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.editText
import org.jetbrains.anko.*
import ru.korablik.color
import ru.korablik.resIdForAttr

/**
 * Created by vkirillov on 20.11.2017.
 */
class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

class SearchViewHolderUI : AnkoComponent<ViewGroup> {

    @SuppressLint("NewApi")
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT,
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT).apply {
                leftMargin = dip(16)
                rightMargin = dip(16)
                topMargin = dip(24)
            })
            backgroundResource = R.drawable.search_item_background

            imageView {
                Glide.with(this).load(R.drawable.ic_search).into(this)
            }.lparams(ViewGroup.MarginLayoutParams(dip(24), dip(24)).apply {
                topMargin = dip(12)
                bottomMargin = dip(12)
                leftMargin = dip(16)
                rightMargin = dip(16)
                marginStart = dip(16)
                marginEnd = dip(16)
            })

            editText {
                hintResource = R.string.content_search_hint
                background = null
                textSize = 16F
                singleLine = true
                maxLines = 1
                setHintTextColor(ctx.color(R.color.colorSearchItemHint))
            }.lparams {
                width = 0
                weight = 1F
                gravity = Gravity.CENTER_VERTICAL
            }

            imageView {
                Glide.with(this).load(R.drawable.ic_scan).into(this)
            }.lparams(ViewGroup.MarginLayoutParams(dip(24), dip(24)).apply {
                topMargin = dip(12)
                bottomMargin = dip(12)
                leftMargin = dip(16)
                rightMargin = dip(16)
                marginStart = dip(16)
                marginEnd = dip(16)
            })

            doFromSdk(21) {
                translationZ = dip(4).toFloat()
                elevation = dip(2).toFloat()
            }

        }
    }
}