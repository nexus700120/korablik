package ru.korablik.adapter.holder

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import ru.korablik.R
import ru.korablik.color
import ru.korablik.domain.ContactPayload

/**
 * Created by vkirillov on 23.11.2017.
 */
class ContactViewHolder(private val ui: ContactViewHolderUI): RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: ContactPayload) {
        if (payload.iconResId != -1) {
            Glide.with(ui.iconView).load(payload.iconResId).into(ui.iconView)
        }
        ui.titleView.text = payload.title
        ui.subtitleView.text = payload.subTitle
    }
}

class ContactViewHolderUI : AnkoComponent<ViewGroup> {

    lateinit var root: View
    lateinit var iconView: ImageView
    lateinit var titleView: TextView
    lateinit var subtitleView: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = linearLayout {
            lparams {
                topMargin = dip(1)
            }
            backgroundColor = Color.WHITE

            iconView = imageView{
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams {
                width = dip(24)
                height = dip(24)
                topMargin = dip(16)
                leftMargin = dip(16)
            }

            verticalLayout {
                titleView = textView {
                    textSize = 16F
                    setTextColor(Color.BLACK)
                }

                subtitleView = textView {
                    textSize = 14F
                    setTextColor(ctx.color(R.color.colorContactSubtitle))
                }.lparams {
                    topMargin = dip(8)
                    bottomMargin = dip(14)
                }
            }.lparams {
                width = 0
                weight = 1F
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                topMargin = dip(16)
                leftMargin = dip(16)
                rightMargin = dip(16)
            }
        }
        return@with root
    }

}