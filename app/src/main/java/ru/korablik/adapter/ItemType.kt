package ru.korablik.adapter

/**
 * Created by vkirillov on 22.11.2017.
 */
class ItemType<T>(val type: Int, val payload: T? = null) {

    companion object {
        val searchType = 1
        val headerType = 2
        val productCarouselType = 3
        val sharesType = 4
        val genderChooserType = 5
        val giftType = 6
        val cardType = 7
        val contactType = 8
        val set = 9
        val space = 10
    }
}