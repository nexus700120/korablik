package ru.korablik.domain

import android.graphics.Color

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

class ProductPayload(val statusVisible: Boolean = false,
                     val statusBackgroundColorRes: Int = -1,
                     val textStatus: String? = null,
                     val imageStatusResId: Int? = null,
                     val productDescription: String? = null,
                     val price: String? = null,
                     val priceColor: Int = Color.BLACK,
                     val priceDesc: String? = null,
                     val priceDescStriked: Boolean = false,
                     val additionalDescription: String? = null)

class SetItemPayload(val firstImageResId: Int? = null,
                     val secondImageResId: Int? = null,
                     val thirdImageResId: Int? = null,
                     val description: String? = null,
                     val price: String? = null)

class SpacePayload(val spaceInDp: Int)

