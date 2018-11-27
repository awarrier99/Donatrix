package edu.gatech.donatrix.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ItemManager {
    private final List<Item> items;

    /**
     * Constructor
     */
    public ItemManager() {
        this.items = new ArrayList<>();
    }

    //Paul: Shouldn't getItems be a method of Inventory?
    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    //Paul: Shouldn't addItem be a method of Inventory?
    public void addItem(Item item) {
        this.items.add(item);
    }

    //Paul: Shouldn't removeITem be a method of Inventory?
    public void removeItem(Item item) {
        this.items.remove(item);
    }
}