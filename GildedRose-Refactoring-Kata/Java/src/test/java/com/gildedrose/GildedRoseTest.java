package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    boolean isNegative (int n){
        return n < 0;
    }

    @Test
    void qualityNeverNegative() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (Item item : app.items) {
            System.out.println(item);
            assertEquals(false, isNegative(item.quality));
        }
    }

    @Test
    void agedBrieQualityIncreases () {
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 0), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void qualityOfItemNeverMoreThan50 () {
        Item[] items = new Item[] {
            new Item("Aged Brie", 2, 50), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void SulfurasNeverChange () {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 2, 80), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void qualityDegradesTwiceAfterSellDate () {
        Item[] items = new Item[] {
            new Item("Elixir of the Mongoose", 0, 10), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
     }

    @Test
    void backstagePassesQualityDropTo0AfterConcert () {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityIncreasesBy3Before5Days () {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityIncreasesBy2Before10Days () {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void ConjuredQualityDegradesTwice () {
        Item[] items = new Item[] {
            new Item("Conjured Mana Cake", 10, 20), //
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
    }

}
