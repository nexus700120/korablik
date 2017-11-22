package ru.korablik

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue

/**
 * Created by vkirillov on 20.11.2017.
 */
inline fun Context.resIdForAttr(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}

fun Context.color(colorResId: Int): Int = ContextCompat.getColor(this, colorResId)