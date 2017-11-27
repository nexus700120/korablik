package ru.korablik.anko

import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView
import ru.korablik.view.AutoScrollViewPager

/**
 * Created by vkirillov on 27.11.2017.
 */
inline fun ViewManager.autoScrollViewPager() = autoScrollViewPager { }

inline fun ViewManager.autoScrollViewPager(init: AutoScrollViewPager.() -> Unit) =
        ankoView({ ctx -> AutoScrollViewPager(ctx) }, 0, init)