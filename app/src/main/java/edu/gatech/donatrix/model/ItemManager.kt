package edu.gatech.donatrix.model

import java.util.ArrayList
import java.util.Collections

internal class ItemManager {
    private val items: MutableList<Item>

    /**
     * Constructor
     */
    init {
        this.items = ArrayList()
    }

    //Paul: Shouldn't getItems be a method of Inventory?
    fun getItems(): List<Item> {
        return Collections.unmodifiableList(this.items)
    }

    //Paul: Shouldn't addItem be a method of Inventory?
    fun addItem(item: Item) {
        this.items.add(item)
    }

    //Paul: Shouldn't removeITem be a method of Inventory?
    fun removeItem(item: Item) {
        this.items.remove(item)
    }
}