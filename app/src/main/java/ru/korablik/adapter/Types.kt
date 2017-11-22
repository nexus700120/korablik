package ru.korablik.adapter

/**
 * Created by Vitaly on 21.11.2017.
 */
sealed class ItemType<T> {
    var payload: T? = null
    val search = 1

    companion object {
        val search = 1
    }
}
