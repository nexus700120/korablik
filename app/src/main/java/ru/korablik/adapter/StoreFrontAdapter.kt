package ru.korablik.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.korablik.adapter.holder.SearchViewHolder
import ru.korablik.adapter.holder.SearchViewHolderUI
import org.jetbrains.anko.AnkoContext
import ru.korablik.adapter.holder.HeaderViewHolder
import ru.korablik.adapter.holder.HeaderViewHolderUI

/**
 * Created by vkirillov on 20.11.2017.
 */
class StoreFrontAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val searchItem = 1
    private val headerItem = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        when(type) {
            headerItem -> (holder as HeaderViewHolder).bind()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            searchItem -> SearchViewHolder(SearchViewHolderUI()
                    .createView(AnkoContext.create(parent.context, parent)))
            else -> HeaderViewHolder(HeaderViewHolderUI()
                    .apply { createView(AnkoContext.create(parent.context, parent)) })
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> searchItem
            else -> headerItem
        }
    }

}