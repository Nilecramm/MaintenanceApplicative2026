package com.gildedrose;

import java.util.Map;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final Map<String, ItemUpdater> updaters = Map.of(
        AGED_BRIE, new AgedBrie(),
        BACKSTAGE_PASSES, new BackstagePasses(),
        SULFURAS, new Sulfuras()
    );

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        //on boucle sur tout les items
        for (Item item : items) {
            //on récupère l'item updater correspondant à l'item
            ItemUpdater updater = updaters.getOrDefault(item.name, new DefaultItem());
            //on met à jour l'item
            updater.update(item);
        }
    }
}
