package ru.korablik.adapter.holder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color
import ru.korablik.domain.CardPayload

/**
 * Created by vkirillov on 23.11.2017.
 */
class CardViewHolder(private val ui: CardViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: CardPayload) {
        if (payload.iconResId != -1) {
            Glide.with(ui.cardIcon).load(payload.iconResId).into(ui.cardIcon)
        }
        ui.title.text = payload.title
    }
}

class CardViewHolderUI : AnkoComponent<ViewGroup> {

    lateinit var root: View
    lateinit var cardIcon: ImageView
    lateinit var title: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = linearLayout {
            lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                topMargin = dip(1)
            }
            backgroundColor = Color.WHITE

            cardIcon = imageView {
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams {
                width = dip(64)
                height = dip(40)
                leftMargin = dip(16)
                topMargin = dip(24)
                bottomMargin = dip(24)
                gravity = Gravity.CENTER_VERTICAL
            }

            title = textView {
                textSize = 16F
                setTextColor(ctx.color(R.color.colorCardTitle))
            }.lparams {
                width = 0
                weight = 1F
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                gravity = Gravity.CENTER_VERTICAL
                rightMargin = dip(16)
                leftMargin = dip(16)
            }

            imageView {
                Glide.with(this).load(R.drawable.ic_arrow_list).into(this)
            }.lparams {
                width = dip(24)
                height = dip(24)
                rightMargin = dip(16)
                gravity = Gravity.CENTER_VERTICAL
            }
        }
        return@with root
    }
}