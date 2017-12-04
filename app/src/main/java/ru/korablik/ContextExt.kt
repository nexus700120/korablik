package ru.korablik

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue

/**
 * Created by vkirillov on 20.11.2017.
 */

fun Context.color(colorResId: Int): Int = ContextCompat.getColor(this, colorResId)