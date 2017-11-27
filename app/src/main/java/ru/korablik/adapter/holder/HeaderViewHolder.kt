package ru.korablik.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color
import ru.korablik.domain.HeaderPayload

/**
 * Created by Vitaly on 21.11.2017.
 */
class HeaderViewHolder(private val ui: HeaderViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: HeaderPayload) {
        ui.title.text = payload.title
        ui.actionButton.visibility = if (payload.actionButtonVisible) View.VISIBLE else View.GONE
        ui.actionButton.text = payload.actButtonTitle
    }
}

class HeaderViewHolderUI : AnkoComponent<ViewGroup> {
    lateinit var root: View
    lateinit var title: TextView
    lateinit var actionButton: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root =  frameLayout {
            lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT

                height = dip(48)
            }
            title = textView {
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14F
                textColor = Color.BLACK
                setAllCaps(true)
            }.lparams {
                leftMargin = dip(16)
                rightMargin = dip(16)
                bottomMargin = dip(12)
                gravity = Gravity.BOTTOM
            }

            actionButton = textView {
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14F
                textColor = ctx.color(R.color.colorActionButton)
                setAllCaps(true)
            }.lparams {
                gravity = Gravity.END or Gravity.BOTTOM
                leftMargin = dip(16)
                rightMargin = dip(16)
                bottomMargin = dip(12)
            }
        }
        return@with root
    }
}