package ru.korablik

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.titleResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by vkirillov on 20.11.2017.
 */
class MainActivityUI : AnkoComponent<MainActivity> {

    lateinit var toolbar: Toolbar
    lateinit var recycler: RecyclerView

    @SuppressLint("NewApi")
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            toolbar = toolbar {
                isFocusable = true
                isFocusableInTouchMode = true
                titleResource = R.string.content_toolbar_title_main
                setTitleTextColor(ctx.color(R.color.colorToolbarTitle))
                backgroundColor = Color.WHITE
                navigationIconResource = R.drawable.ic_menu_burger
                setNavigationOnClickListener {}
                inflateMenu(R.menu.basket_menu)
                setOnMenuItemClickListener { true }
                doFromSdk(21) {
                    translationZ = dip(4).toFloat()
                    elevation = dip(2).toFloat()
                }

            }
            recycler = recyclerView().lparams {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
    }

}