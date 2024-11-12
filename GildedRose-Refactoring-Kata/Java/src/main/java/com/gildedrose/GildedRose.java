package com.gildedrose;

class GildedRose {
    Item[] items;

    boolean isAgedBrie(String name) {
        return name.equals("Aged Brie");
    }

    boolean isBackstage(String name) {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    boolean isSulfuras(String name) {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    boolean isConjured(String name) {
        return name.equals("Conjured Mana Cake");
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            int originalSellIn = item.sellIn;
            int originalQuality = item.quality;

            if (!isAgedBrie(item.name)
                && !isBackstage(item.name)) {
                item.quality = item.quality - 1;
            } else {
                item.quality = item.quality + 1;

                if (isBackstage(item.name)) {
                    if (item.sellIn < 11) {
                        item.quality = item.quality + 1;
                    }

                    if (item.sellIn < 6) {
                        item.quality = item.quality + 1;
                    }
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (!isAgedBrie(item.name)) {
                    if (!isBackstage(item.name)) {
                        item.quality = item.quality - 1;
                    } else {
                        item.quality = 0;
                    }
                } else {
                    item.quality = item.quality + 1;
                }
            }

            // Conjured degrades 2 times (1 covered as normal above, and one here)
            if (isConjured(item.name)) {
                item.quality = item.quality - 1;
            }

            // quality is never < 0 OR > 50
            item.quality = Math.min(50, Math.max(0, item.quality));

            if (isSulfuras(item.name)) {
                item.quality = originalQuality;
                item.sellIn = originalSellIn;
            }

        }
    }
}
