package com.magasin;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
class MagasinTest {

    @Test
    void testPouvoirsMagiquesBeforeSellIn() {
        int peremption = 10;
        int quality = 10;
        Item[] items = new Item[] { new Item("Pouvoirs magiques", peremption, quality)};
        Magasin app = new Magasin(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }
    @Test
    void testPouvoirsMagiquesAfterSellIn() {
        int peremption = -1;
        int quality = 10;
        Item[] items = new Item[] { new Item("Pouvoirs magiques", peremption, quality)};
        Magasin app = new Magasin(items);

        app.updateQuality();

        assertEquals(peremption - 1, items[0].sellIn);
        assertEquals(6, items[0].quality);
    }

    @Test
    void testItems() {
        Random random = new Random();
        int peremption = random.nextInt(100 - 0 + 1) + 0;
        int quality = random.nextInt(100 - 0 + 1) + 0;
        Item[] items = new Item[] { new Item("yaourt", peremption, quality)};
        Magasin app = new Magasin(items);
        // WHEN
        app.updateQuality();
        // THEN
        assertsItems(peremption, quality, items);
    }

    private void assertsItems(int peremption, int quality, Item[] items) {
        if (peremption > 0) {
            /* sellIn update */
            assertEquals(peremption - 1, items[0].sellIn);
            assertEquals(quality - 1, items[0].quality);
        } else if (peremption < 0) {
            /* quality update */
            assertEquals(quality - 2, items[0].quality);
            assertEquals(peremption, items[0].sellIn);
        }
    }

    @Test
    void testKrypto() {
        Random random = new Random();
        int peremption = random.nextInt(100 - 0 + 1) + 0;
        int quality = 50;
        Item[] items = new Item[] { new Item("Kryptonite", peremption, quality)};
        Magasin app = new Magasin(items);
        // WHEN
        app.updateQuality();
        // THEN
        /* sellIn update */
        assertEquals(peremption, items[0].sellIn);
        /* quality update */
        assertEquals(quality, items[0].quality);
    }

    @Test
    void testComteSellIn() {
        int preremption = 0;
        int quality = 50;
        Item[] items = new Item[] { new Item("Comté", preremption, quality)};
        Magasin app = new Magasin(items);
        // WHEN
        app.updateQuality();
        // THEN
        /* sellIn update */
        assertEquals(preremption - 1, items[0].sellIn);
        /* quality update */
        assertEquals(quality, items[0].quality);
    }

    @Test
    void testConcertBeforeSellIn() {
        Item[] items = new Item[]{new Item("Pass VIP Concert", 15, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertConcertBeforeSellIn(15, 20, items);
    }

    @Test
    void testConcertWithin10Days() {
        Item[] items = new Item[]{new Item("Pass VIP Concert", 10, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertConcertWithin10Days(10, 20, items);
    }

    @Test
    void testConcertWithin5Days() {
        Item[] items = new Item[]{new Item("Pass VIP Concert", 5, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertConcertWithin5Days(5, 20, items);
    }

    @Test
    void testConcertAfterSellIn() {
        Item[] items = new Item[]{new Item("Pass VIP Concert", -1, 20)};
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertConcertAfterSellIn(-1, 20, items);
    }

    private void assertConcertBeforeSellIn(int sellIn, int quality, Item[] items) {
        assertEquals(quality + 1, items[0].quality);
        assertEquals(sellIn - 1, items[0].sellIn);
    }

    private void assertConcertWithin10Days(int sellIn, int quality, Item[] items) {
        assertEquals(quality + 2, items[0].quality);
        assertEquals(sellIn - 1, items[0].sellIn);
    }

    private void assertConcertWithin5Days(int sellIn, int quality, Item[] items) {
        assertEquals(quality + 3, items[0].quality);
        assertEquals(sellIn - 1, items[0].sellIn);
    }

    private void assertConcertAfterSellIn(int sellIn, int quality, Item[] items) {
        assertEquals(0, items[0].quality);
        assertEquals(sellIn - 1, items[0].sellIn);
    }





}
