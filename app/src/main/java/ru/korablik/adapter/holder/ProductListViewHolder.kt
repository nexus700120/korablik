package ru.korablik.adapter.holder

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import ru.korablik.R
import ru.korablik.color
import ru.korablik.domain.ProductPayload

/**
 * Created by vkirillov on 22.11.2017.
 */
class ProductViewHolder(private val ui: ProductUI) : RecyclerView.ViewHolder(ui.root) {

    init {
        ui.root.layoutManager = LinearLayoutManager(ui.root.context,
                LinearLayoutManager.HORIZONTAL, false)
        ui.root.adapter = ProductAdapter()
    }

    fun bind(productList: List<ProductPayload>) {
        (ui.root.adapter as ProductAdapter).apply {
            items = productList
            notifyDataSetChanged()
        }
    }
}

class ProductUI : AnkoComponent<ViewGroup> {

    lateinit var root: RecyclerView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        root = recyclerView{
            lparams {
                bottomMargin = dip(8)
            }
        }
        return@with root
    }
}

class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {
    var items: List<ProductPayload>? = null

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(items?.get(position) ?: return, itemCount)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(ProductItemViewHolder.ProductItemUI().apply {
            createView(AnkoContext.create(parent.context, parent))
        })
    }
}

class ProductItemViewHolder(private val ui: ProductItemUI) : RecyclerView.ViewHolder(ui.root) {

    fun bind(productPayload: ProductPayload, count: Int) {
        ui.statusContainer.visibility = if (productPayload.statusVisible) View.VISIBLE else View.GONE
        if (productPayload.statusBackgroundColorRes != -1) {
            val statusBackgroundDrawable = ui.statusContainer.background
            if (statusBackgroundDrawable is GradientDrawable) {
                statusBackgroundDrawable.setColor(ui.root.context
                        .color(productPayload.statusBackgroundColorRes))
            }
        }

        if (productPayload.textStatus.isNullOrEmpty()) {
            ui.statusText.visibility = View.GONE
        } else {
            ui.statusText.visibility = View.VISIBLE
            ui.statusText.text = productPayload.textStatus
        }
        if (productPayload.imageStatusResId ?: -1 > 0) {
            ui.statusImage.visibility = View.VISIBLE
            Glide.with(ui.statusImage).load(productPayload.imageStatusResId).into(ui.statusImage)
        } else {
            ui.statusImage.visibility = View.GONE
        }
        ui.productDescription.text = productPayload.productDescription
        ui.price.text = productPayload.price
        ui.price.setTextColor(productPayload.priceColor)

        if (!productPayload.priceDesc.isNullOrEmpty()) {
            ui.priceDescription.visibility = View.VISIBLE
            ui.priceDescription.text = productPayload.priceDesc

            if (productPayload.priceDescStriked) {
                ui.priceDescription.paintFlags =
                        ui.priceDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                ui.priceDescription.paintFlags =
                        ui.priceDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        if (productPayload.additionalDescription.isNullOrEmpty()) {
            ui.productDescription.maxLines = 3
            ui.additionalDescription.visibility = View.GONE
        } else {
            ui.productDescription.maxLines = 2
            ui.additionalDescription.visibility = View.VISIBLE
            ui.additionalDescription.text = productPayload.additionalDescription
        }

        (ui.root.layoutParams as ViewGroup.MarginLayoutParams).apply {
            leftMargin = if (adapterPosition == 0) ui.root.dip(16) else ui.root.dip(24)
            rightMargin = if (adapterPosition == count - 1) ui.root.dip(16) else 0
        }
    }

    class ProductItemUI : AnkoComponent<ViewGroup> {
        lateinit var root: View
        lateinit var statusContainer: FrameLayout
        lateinit var statusText: TextView
        lateinit var statusImage: ImageView
        lateinit var productDescription: TextView
        lateinit var price: TextView
        lateinit var priceDescription: TextView
        lateinit var additionalDescription: TextView

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            root = relativeLayout {
                lparams {
                    width = dip(184)
                    height = dip(276)
                }
                setBackgroundResource(R.drawable.product_background)

                imageView {
                    id = R.id.product_image
                    Glide.with(this).load(R.raw.cat).into(this)
                }.lparams {
                    width = dip(120)
                    height = dip(120)
                    topMargin = dip(16)
                    centerHorizontally()
                }

                imageView {
                    Glide.with(this).load(R.drawable.ic_like).into(this)
                }.lparams {
                    width = dip(24)
                    height = dip(24)
                    topMargin = dip(8)
                    rightMargin = dip(8)
                    alignParentRight()
                }

                statusContainer = frameLayout {
                    backgroundResource = R.drawable.status_shape_background

                    statusText = textView {
                        textSize = 12F
                        setTypeface(typeface, Typeface.BOLD)
                        setTextColor(Color.WHITE)
                    }.lparams {
                        gravity = Gravity.CENTER
                    }

                    statusImage = imageView().lparams {
                        height = dip(16)
                        gravity = Gravity.CENTER
                    }
                }.lparams {
                    width = dip(40)
                    height = dip(20)
                    topMargin = dip(8)
                    leftMargin = dip(8)
                }

                textView {
                    id = R.id.buy
                    setTypeface(typeface, Typeface.BOLD)
                    textSize = 14F
                    textColor = Color.WHITE
                    backgroundResource = R.drawable.buy_btn_background
                    textResource = R.string.content_common_buy_basket
                    setAllCaps(true)
                    gravity = Gravity.CENTER
                }.lparams {
                    width = dip(160)
                    height = dip(36)
                    bottomMargin = dip(12)
                    alignParentBottom()
                    centerHorizontally()
                }

                productDescription = textView {
                    id = R.id.product_description
                    textSize = 14F
                    setTextColor(Color.BLACK)
                    ellipsize = TextUtils.TruncateAt.END
                }.lparams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                    leftMargin = dip(12)
                    rightMargin = dip(12)
                    below(R.id.product_image)
                }

                price = textView {
                    id = R.id.product_price
                    textSize = 16F
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams {
                    leftMargin = dip(12)
                    rightMargin = dip(8)
                    topMargin = dip(8)
                    below(R.id.product_description)
                }

                priceDescription = textView {
                    textSize = 12F
                }.lparams {
                    rightOf(price)
                    baselineOf(price)
                }

                additionalDescription = textView {
                    textSize = 12F
                    setTextColor(ctx.color(R.color.colorAdditionalDescription))
                }.lparams {
                    leftMargin = dip(12)
                    rightMargin = dip(12)
                    below(R.id.product_price)
                }
            }
            return@with root
        }
    }
}