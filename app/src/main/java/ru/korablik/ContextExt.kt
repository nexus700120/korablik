package ru.korablik

import android.content.Context
import android.util.TypedValue

/**
 * Created by vkirillov on 20.11.2017.
 */
inline fun Context.resIdForAttr(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}