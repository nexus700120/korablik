package ru.korablik.domain

/**
 * Created by vkirillov on 22.11.2017.
 */
class HeaderPayload(val title: String? = null,
                    val actionButtonVisible: Boolean = false,
                    val actButtonTitle: String? = null)

class CardPayload(val iconResId: Int = -1,
                  val title: String? = null)

class ContactPayload(val iconResId: Int = -1,
                     val title: String? = null,
                     val subTitle: String? = null)

class SharesPayload(val imageResList: List<Int> = listOf())

