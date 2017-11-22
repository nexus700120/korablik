package ru.korablik.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color

/**
 * Created by Vitaly on 21.11.2017.
 */
class HeaderViewHolder(private val ui: HeaderViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind() {
        ui.title.text = adapterPosition.toString()
        ui.actionButton.text = adapterPosition.toString()
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
            }

            actionButton = textView {
                setTypeface(typeface, Typeface.BOLD)
                textSize = 14F
                textColor = ctx.color(R.color.colorActionButton)
                gravity = Gravity.RIGHT
            }
        }
        return@with root
    }
}