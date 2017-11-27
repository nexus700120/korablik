package ru.korablik.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color

/**
 * Created by vkirillov on 23.11.2017.
 */
class GiftViewHolder(ui: GiftViewHolderUI): RecyclerView.ViewHolder(ui.root)

class GiftViewHolderUI : AnkoComponent<ViewGroup> {

    lateinit var root: View

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = verticalLayout {
            lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
            }

            backgroundResource = R.drawable.white_bordered_background

            imageView {
                Glide.with(this).load(R.drawable.ic_gift).into(this)
            }.lparams {
                width = dip(48)
                height = dip(48)
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(24)
            }

            textView {
                textSize = 14F
                textResource = R.string.content_gift_desc
                setTextColor(ctx.color(R.color.colorGiftDescription))
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(12)
                leftMargin = dip(40)
                rightMargin = dip(40)
            }

            textView {
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14F
                textColor = Color.WHITE
                backgroundResource = R.drawable.choice_btn_background
                textResource = R.string.content_gift_action
                setAllCaps(true)
                gravity = Gravity.CENTER
            }.lparams {
                width = dip(152)
                height = dip(36)
                topMargin = dip(16)
                bottomMargin = dip(24)
                gravity = Gravity.CENTER_HORIZONTAL
            }
        }
        return@with root
    }

}