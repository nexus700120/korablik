package ru.korablik.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import ru.korablik.domain.SpacePayload

/**
 * Created by Vitaly on 04.12.2017.
 */
class SpaceViewHolder(private val ui: SpaceViewHolderUI): RecyclerView.ViewHolder(ui.root) {

    fun bind(payload: SpacePayload) {
        (ui.root.layoutParams as ViewGroup.LayoutParams).apply {
            height = ui.root.dip(payload.spaceInDp)
        }
    }
}

class SpaceViewHolderUI : AnkoComponent<ViewGroup> {

    lateinit var root: View

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        root = with(ui) {
            frameLayout {
                lparams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                }
            }
        }
        return root
    }

}