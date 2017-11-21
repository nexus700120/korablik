package ru.korablik

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ru.korablik.adapter.StoreFrontAdapter
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    private lateinit var ui: MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = MainActivityUI()
        ui.setContentView(this)

        ui.recycler.layoutManager = LinearLayoutManager(this)
        ui.recycler.adapter = StoreFrontAdapter()
    }
}
