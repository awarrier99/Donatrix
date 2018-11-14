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

    /**
     * gets items
     * @return items
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    /**
     * adds Items
     * @param item item to add
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * removes items
     * @param item item to remove
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }
}