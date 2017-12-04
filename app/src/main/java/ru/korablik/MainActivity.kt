package ru.korablik

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ru.korablik.adapter.StoreFrontAdapter
import org.jetbrains.anko.setContentView
import ru.korablik.adapter.ItemType
import ru.korablik.domain.*

class MainActivity : AppCompatActivity() {

    private lateinit var ui: MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = MainActivityUI()
        ui.setContentView(this)

        ui.recycler.layoutManager = LinearLayoutManager(this)
        ui.recycler.adapter = StoreFrontAdapter().apply {
            items = getItemList()
        }
    }

    private fun getItemList(): List<ItemType<*>> = listOf(
            ItemType<Unit>(ItemType.searchType),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_promotions))),
            ItemType(ItemType.sharesType, SharesPayload(
                    listOf(R.drawable.bitmap, R.drawable.bitmap, R.drawable.bitmap,
                            R.drawable.bitmap, R.drawable.bitmap, R.drawable.bitmap)
            )),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_recommended),
                    true, getString(R.string.content_common_all))),
            ItemType(ItemType.productCarouselType, getDataForProductList()),
            ItemType<Unit>(ItemType.genderChooserType),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_sale), true,
                    getString(R.string.content_common_all))),
            ItemType(ItemType.productCarouselType, getDataForProductList()),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_new), true,
                    getString(R.string.content_common_all))),
            ItemType(ItemType.productCarouselType, getDataForProductList()),
            ItemType<Unit>(ItemType.giftType),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_top_sales), true,
                    getString(R.string.content_common_all))),
            ItemType(ItemType.productCarouselType, getDataForProductList()),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_gift_cards))),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_red_card,
                    getString(R.string.content_electronic_gift_certificate))),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_gold_card,
                    getString(R.string.content_gift_flexible))),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_blue_card,
                    getString(R.string.content_gift_corporate))),
            ItemType(ItemType.space, SpacePayload(8)),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_set_title),
                    true, getString(R.string.content_common_all))),
            ItemType(ItemType.set, getDataForSetProductList()),
            ItemType(ItemType.headerType, HeaderPayload(getString(R.string.content_common_recently_watched),
                    true, getString(R.string.content_common_all))),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType(ItemType.space, SpacePayload(15)),
            ItemType(ItemType.contactType, ContactPayload(R.drawable.ic_location,
                    getString(R.string.content_address_title),
                    getString(R.string.content_address_desc))),
            ItemType(ItemType.contactType, ContactPayload(R.drawable.ic_call,
                    getString(R.string.content_contact_title),
                    getString(R.string.content_contact_desc))),
            ItemType(ItemType.space, SpacePayload(16))
    )

    private fun getDataForProductList(): List<ProductPayload> = listOf(
            ProductPayload(statusVisible = false,
                    productDescription = "Футболка с коротким рукавом для мальчиков BARKITO",
                    price = "259 \u20BD"),
            ProductPayload(statusVisible = true,
                    statusBackgroundColorRes = R.color.colorStatusRed,
                    textStatus = "-10%",
                    productDescription = "Футболка с коротким рукавом для мальчиков BARKITO",
                    price = "259 \u20BD",
                    priceColor = Color.RED,
                    priceDesc = "400 \u20BD",
                    priceDescStriked = true,
                    additionalDescription = "(выгода 141 \u20BD)"),
            ProductPayload(statusVisible = true,
                    statusBackgroundColorRes = R.color.colorsStatusGold,
                    imageStatusResId = R.drawable.ic_credit_card,
                    productDescription = "Футболка с коротким рукавом для мальчиков BARKITO",
                    price = "259 \u20BD",
                    priceColor = Color.RED,
                    priceDesc = "без карты 300 \u20BD",
                    additionalDescription = "(выгода 141 \u20BD)"),
            ProductPayload(statusVisible = true,
                    statusBackgroundColorRes = R.color.colorsStatusGreen,
                    textStatus = "NEW",
                    productDescription = "Футболка с коротким рукавом для мальчиков BARKITO",
                    price = "259 \u20BD"),
            ProductPayload(statusVisible = true,
                    statusBackgroundColorRes = R.color.colorStatusOrange,
                    textStatus = "2+1",
                    productDescription = "Футболка с коротким рукавом для мальчиков BARKITO",
                    price = "259 \u20BD")
    )

    private fun getDataForSetProductList(): List<SetItemPayload> = listOf(
            SetItemPayload(
                    firstImageResId = R.raw.sweater,
                    secondImageResId = R.raw.pants,
                    thirdImageResId = R.raw.socks,
                    description = "Комплект Barkito Водолазка + пижама + носки",
                    price = "259 \u20BD"
            ),
            SetItemPayload(
                    firstImageResId = R.raw.sweater,
                    secondImageResId = R.raw.pants,
                    thirdImageResId = R.raw.socks,
                    description = "Комплект Barkito Водолазка + пижама + носки",
                    price = "259 \u20BD"
            ),
            SetItemPayload(
                    firstImageResId = R.raw.sweater,
                    secondImageResId = R.raw.pants,
                    thirdImageResId = R.raw.socks,
                    description = "Комплект Barkito Водолазка + пижама + носки",
                    price = "259 \u20BD"
            ),
            SetItemPayload(
                    firstImageResId = R.raw.sweater,
                    secondImageResId = R.raw.pants,
                    thirdImageResId = R.raw.socks,
                    description = "Комплект Barkito Водолазка + пижама + носки",
                    price = "259 \u20BD"
            ),
            SetItemPayload(
                    firstImageResId = R.raw.sweater,
                    secondImageResId = R.raw.pants,
                    thirdImageResId = R.raw.socks,
                    description = "Комплект Barkito Водолазка + пижама + носки",
                    price = "259 \u20BD"
            )
    )
}
