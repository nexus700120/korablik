package ru.korablik

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ru.korablik.adapter.StoreFrontAdapter
import org.jetbrains.anko.setContentView
import ru.korablik.adapter.ItemType
import ru.korablik.domain.CardPayload
import ru.korablik.domain.ContactPayload
import ru.korablik.domain.HeaderPayload
import ru.korablik.domain.SharesPayload

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
            ItemType(ItemType.headerType, HeaderPayload("Акции")),
            ItemType(ItemType.sharesType, SharesPayload(
                    listOf(R.drawable.bitmap, R.drawable.bitmap, R.drawable.bitmap,
                            R.drawable.bitmap, R.drawable.bitmap, R.drawable.bitmap)
            )),
            ItemType(ItemType.headerType, HeaderPayload("Рекомендуем вам", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType<Unit>(ItemType.genderChooserType),
            ItemType(ItemType.headerType, HeaderPayload("Распродажа", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType(ItemType.headerType, HeaderPayload("Новинки", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType<Unit>(ItemType.giftType),
            ItemType(ItemType.headerType, HeaderPayload("Топ продаж", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType(ItemType.headerType, HeaderPayload("Подарочные карты")),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_red_card, "Электронный подарочный сертификат")),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_gold_card, "Подарочная карта гибкого номинала")),
            ItemType(ItemType.cardType, CardPayload(R.drawable.ic_blue_card, "Корпоративные подарочные карты")),
            ItemType(ItemType.headerType, HeaderPayload("Готовые комплекты", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType(ItemType.headerType, HeaderPayload("Вы недавно смотрели", true, "Все")),
            ItemType<Unit>(ItemType.productCarouselType),
            ItemType(ItemType.contactType, ContactPayload(R.drawable.ic_location, "Адреса магазинов", "Найдите магазины, которые находятся с рядом с вами!")),
            ItemType(ItemType.contactType, ContactPayload(R.drawable.ic_call, "Связь с нами", "Присоединяйтесь к нам в соц. сетях и следите за новостями!"))
    )
}
