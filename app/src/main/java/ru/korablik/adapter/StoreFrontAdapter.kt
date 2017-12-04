package ru.korablik.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import ru.korablik.adapter.holder.*
import ru.korablik.domain.*

/**
 * Created by vkirillov on 20.11.2017.
 */
class StoreFrontAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ItemType<*>>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        val item = items?.get(position) ?: return
        item.payload ?: return
        @Suppress("UNCHECKED_CAST")
        when (type) {
            ItemType.headerType -> (holder as HeaderViewHolder).bind(item.payload as HeaderPayload)
            ItemType.cardType -> (holder as CardViewHolder).bind(item.payload as CardPayload)
            ItemType.contactType -> (holder as ContactViewHolder).bind(item.payload as ContactPayload)
            ItemType.sharesType -> (holder as PromotionViewHolder).bind(item.payload as SharesPayload)
            ItemType.productCarouselType -> (holder as ProductViewHolder)
                    .bind(item.payload as List<ProductPayload>)
            ItemType.set -> (holder as SetViewHolder).bind(item.payload as List<SetItemPayload>)
            ItemType.space -> (holder as SpaceViewHolder).bind(item.payload as SpacePayload)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ankoContext = AnkoContext.create(parent.context, parent)
        return when (viewType) {
            ItemType.searchType -> SearchViewHolder(SearchViewHolderUI()
                    .createView(ankoContext))
            ItemType.headerType -> HeaderViewHolder(HeaderViewHolderUI()
                    .apply { createView(ankoContext) })
            ItemType.productCarouselType -> ProductViewHolder(ProductUI().apply {
                createView(ankoContext)
            })
            ItemType.sharesType -> PromotionViewHolder(PromotionViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.giftType -> GiftViewHolder(GiftViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.cardType -> CardViewHolder(CardViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.contactType -> ContactViewHolder(ContactViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.set -> SetViewHolder(SetViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.genderChooserType -> GenderViewHolder(GenderViewHolderUI().apply {
                createView(ankoContext)
            })
            ItemType.space -> SpaceViewHolder(SpaceViewHolderUI().apply {
                createView(ankoContext)
            })
            else -> object : RecyclerView.ViewHolder(null) {}
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun getItemViewType(position: Int): Int = items?.get(position)?.type ?: -1
}