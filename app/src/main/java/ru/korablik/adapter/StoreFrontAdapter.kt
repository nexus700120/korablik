package ru.korablik.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import ru.korablik.adapter.holder.*
import ru.korablik.domain.CardPayload
import ru.korablik.domain.ContactPayload
import ru.korablik.domain.HeaderPayload
import ru.korablik.domain.SharesPayload

/**
 * Created by vkirillov on 20.11.2017.
 */
class StoreFrontAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ItemType<*>>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        val item = items?.get(position) ?: return
        when(type) {
            ItemType.headerType -> (holder as HeaderViewHolder).bind(item.payload as HeaderPayload)
            ItemType.cardType -> (holder as CardViewHolder).bind(item.payload as CardPayload)
            ItemType.contactType -> (holder as ContactViewHolder).bind(item.payload as ContactPayload)
            ItemType.sharesType -> (holder as SharesViewHolder).bind(item.payload as SharesPayload)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ankoContext = AnkoContext.create(parent.context, parent)
        return when(viewType) {
            ItemType.searchType -> SearchViewHolder(SearchViewHolderUI()
                    .createView(ankoContext))
            ItemType.headerType -> HeaderViewHolder(HeaderViewHolderUI()
                    .apply { createView(ankoContext) })
            ItemType.productCarouselType -> ProductCarouselViewHolder(ProductCarouselUI().apply {
                createView(ankoContext)
            })
            ItemType.sharesType -> SharesViewHolder(SharesViewHolderUI().apply {
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
            else -> GenderViewHolder(GenderViewHolderUI().apply { createView(ankoContext) })
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun getItemViewType(position: Int): Int = items?.get(position)?.type ?: -1

}