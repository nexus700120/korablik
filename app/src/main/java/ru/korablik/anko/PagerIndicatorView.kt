package ru.korablik.anko

import android.view.ViewManager
import com.rd.PageIndicatorView
import org.jetbrains.anko.custom.ankoView

/**
 * Created by vkirillov on 27.11.2017.
 */
inline fun ViewManager.pagerIndicator() = pagerIndicator { }

inline fun ViewManager.pagerIndicator(init: PageIndicatorView.() -> Unit) =
        ankoView({ ctx -> PageIndicatorView(ctx) }, 0, init)