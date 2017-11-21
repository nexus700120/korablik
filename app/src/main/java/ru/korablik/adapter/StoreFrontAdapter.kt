package ru.korablik.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.korablik.adapter.holder.SearchViewHolder
import ru.korablik.adapter.holder.SearchViewHolderUI
import org.jetbrains.anko.AnkoContext

/**
 * Created by vkirillov on 20.11.2017.
 */
class StoreFrontAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val searchItem = 1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(SearchViewHolderUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return searchItem
    }

}