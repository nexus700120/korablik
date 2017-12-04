package ru.korablik.adapter.holder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color

/**
 * Created by vkirillov on 22.11.2017.
 */
class GenderViewHolder(ui: GenderViewHolderUI) : RecyclerView.ViewHolder(ui.root)

class GenderViewHolderUI : AnkoComponent<ViewGroup> {

    lateinit var root: View
    lateinit var desc: TextView
    lateinit var boy: TextView
    lateinit var girl: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = relativeLayout {
            lparams(RelativeLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
                topMargin = dip(16)
            })
            backgroundResource = R.drawable.white_bordered_background

            desc = textView {
                id = R.id.desc
                textSize = 14F
                gravity = Gravity.CENTER_HORIZONTAL
                textResource = R.string.content_gender_desc
                setTextColor(ctx.color(R.color.colorDescriptionGender))
            }.lparams {
                addRule(RelativeLayout.CENTER_HORIZONTAL)
                topMargin = dip(16)
                bottomMargin = dip(16)
                leftMargin = dip(32)
                rightMargin = dip(32)
            }

            linearLayout {
                frameLayout {
                    boy = textView {
                        gravity = Gravity.CENTER
                        text = ctx.getText(R.string.content_common_boy)
                        backgroundResource = R.drawable.boy_background
                        textSize = 14F
                        setTextColor(Color.WHITE)
                    }.lparams {
                        width = dip(120)
                        height = dip(36)
                        leftMargin = dip(16)
                        gravity = Gravity.CENTER
                    }
                }.lparams {
                    width = 0
                    weight = 1F
                }

                frameLayout {
                    girl = textView {
                        gravity = Gravity.CENTER
                        text = ctx.getText(R.string.content_common_girl)
                        backgroundResource = R.drawable.girl_background
                        textSize = 14F
                        setTextColor(Color.WHITE)
                    }.lparams {
                        width = dip(120)
                        height = dip(36)
                        rightMargin = dip(16)
                        gravity = Gravity.CENTER
                    }
                }.lparams {
                    width = 0
                    weight = 1F
                }
            }.lparams {
                width = RelativeLayout.LayoutParams.MATCH_PARENT
                bottomPadding = dip(16)
                below(desc)
            }
        }
        return@with root
    }
}