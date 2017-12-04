package ru.korablik.adapter.holder

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import ru.korablik.R
import ru.korablik.domain.SetItemPayload

/**
 * Created by Vitaly on 04.12.2017.
 */
class SetViewHolder(private val ui: SetViewHolderUI) : RecyclerView.ViewHolder(ui.root) {

    init {
        ui.root.layoutManager = LinearLayoutManager(ui.root.context,
                LinearLayoutManager.HORIZONTAL, false)
        ui.root.adapter = SetAdapter()
    }

    fun bind(productList: List<SetItemPayload>) {
        (ui.root.adapter as SetAdapter).apply {
            items = productList
            notifyDataSetChanged()
        }
    }
}

class SetViewHolderUI : AnkoComponent<ViewGroup> {
    lateinit var root: RecyclerView

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        root = with(ui) { recyclerView() }
        return root
    }
}

class SetAdapter : RecyclerView.Adapter<SetAdapterViewHolder>() {

    var items: List<SetItemPayload> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAdapterViewHolder {
        return SetAdapterViewHolder(SetAdapterUi().apply {
            createView(AnkoContext.create(parent.context, parent))
        })
    }

    override fun onBindViewHolder(holder: SetAdapterViewHolder, position: Int) {
        holder.bind(items[position], itemCount)
    }

    override fun getItemCount(): Int = items.size
}

class SetAdapterViewHolder(private val ui: SetAdapterUi) : RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: SetItemPayload, count: Int) {
        payload.firstImageResId?.let {
            Glide.with(ui.poster1).load(it).into(ui.poster1)
        }
        payload.secondImageResId?.let {
            Glide.with(ui.poster2).load(it).into(ui.poster2)
        }
        payload.thirdImageResId?.let {
            Glide.with(ui.poster3).load(it).into(ui.poster3)
        }
        ui.description.text = payload.description
        ui.price.text = payload.price

        (ui.root.layoutParams as ViewGroup.MarginLayoutParams).apply {
            leftMargin = if (adapterPosition == 0) ui.root.dip(16) else ui.root.dip(24)
            rightMargin = if (adapterPosition == count - 1) ui.root.dip(16) else 0
        }
    }
}

class SetAdapterUi : AnkoComponent<ViewGroup> {
    lateinit var root: View
    lateinit var description: TextView
    lateinit var poster1: ImageView
    lateinit var poster2: ImageView
    lateinit var poster3: ImageView
    lateinit var price: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        root = with(ui) {
            relativeLayout {
                lparams {
                    width = dip(302)
                    height = dip(224)
                }
                backgroundResource = R.drawable.product_background

                poster1 = imageView {
                    id = R.id.poster1
                }.lparams {
                    width = dip(90)
                    height = dip(78)
                    topMargin = dip(16)
                    leftMargin = dip(8)
                }

                poster2 = imageView {
                    id = R.id.poster2
                }.lparams {
                    width = dip(90)
                    height = dip(78)
                    topMargin = dip(16)
                    leftMargin = dip(8)
                    rightOf(poster1)
                }

                poster3 = imageView {
                    id = R.id.poster3
                }.lparams {
                    width = dip(90)
                    height = dip(78)
                    topMargin = dip(16)
                    leftMargin = dip(8)
                    rightOf(poster2)
                }

                description = textView {
                    id = R.id.desc
                    textSize = 14F
                    setTextColor(Color.BLACK)
                }.lparams {
                    leftMargin = dip(12)
                    rightMargin = dip(12)
                    topMargin = dip(4)
                    below(poster1)
                }

                price = textView {
                    textSize = 16F
                    setTextColor(Color.BLACK)
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams {
                    leftMargin = dip(12)
                    topMargin = dip(8)
                    below(R.id.desc)
                }

                textView {
                    setTypeface(typeface, Typeface.BOLD)
                    textSize = 14F
                    textColor = Color.WHITE
                    backgroundResource = R.drawable.buy_btn_background
                    textResource = R.string.content_common_buy_basket
                    setAllCaps(true)
                    gravity = Gravity.CENTER
                }.lparams {
                    width = dip(278)
                    height = dip(36)
                    bottomMargin = dip(12)
                    alignParentBottom()
                    centerHorizontally()
                }
            }
        }
        return root
    }
}