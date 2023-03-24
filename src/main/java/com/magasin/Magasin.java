package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

/* ***************MOI******************* */

//    public void updateQuality() {
//        for (int i = 0; i < items.length; i++) {
//            updateQualityItemsWhileValid(i);
//            updateSellInItems(i);
//            updateQualityItemsWhileExpired(i);
//        }
//    }

//    private void updateQualityItemsWhileValid(int i) {
//        if (!items[i].name.equals("Comté")
//                && !items[i].name.equals("Pass VIP Concert")) {
//            updateQualityBasicItems(i);
//        } else if (items[i].name.equals("Pouvoirs magiques")) {
////            updateQualityPouvoirsMagiques();
//        } else {
//            if (items[i].quality < 50) {
//                updateQualityBy1(i);
//                updateQualityConcerts(i);
//            }
//        }
//    }
//
//    private void updateQualityBasicItems(int i){
//        if (items[i].quality > 0 && !items[i].name.equals("Kryptonite")) {
//                items[i].quality = items[i].quality - 1;
//        }
//    }
//
//    private void updateQualityBy1(int i) {
//        items[i].quality = items[i].quality + 1;
//    }
//    private void updateQualityConcerts(int i) {
//        if (items[i].name.equals("Pass VIP Concert")
//                && items[i].quality < 50) {
//            updateQualityConcertBy2(i);
//            updateQualityConcertBy3(i);
//        }
//    }
//
//    private void updateQualityConcertBy2(int i) {
//        int daysBeforeExpire = 11;
//        if (items[i].sellIn < daysBeforeExpire) {
//            items[i].quality = items[i].quality + 1;
//        }
//    }
//
//    private void updateQualityConcertBy3(int i) {
//        int daysBeforeExpire = 6;
//        if (items[i].sellIn < daysBeforeExpire) {
//            items[i].quality = items[i].quality + 1;
//        }
//    }
//
//    private void updateSellInItems(int i) {
//        if (!items[i].name.equals("Kryptonite")) {
//            items[i].sellIn = items[i].sellIn - 1;
//        }
//    }
//
//    private void updateQualityItemsWhileExpired(int i) {
//        if (items[i].sellIn < 0) {
//            if (!items[i].name.equals("Comté") && !items[i].name.equals("Pass VIP Concert")) {
//                updateQualityBasicItems(i);
//            } else if (items[i].name.equals("Pass VIP Concert")) {
//                setQualityToZero(i);
//            }
//            updateQualityComte(i);
//        }
//
//    }
//
//    private void setQualityToZero(int i) {
//        items[i].quality = items[i].quality - items[i].quality;
//    }
//
//    private void updateQualityComte(int i) {
//        if(items[i].name.equals("Comté") && items[i].quality < 50) {
//            updateQualityBy1(i);
//        }
//
//    }

    /* ***************CHATGPT******************* */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Comté")) {
            updateComteQuality(item);
        } else if (item.name.equals("Pass VIP Concert")) {
            updatePassVipConcertQuality(item);
        } else if (item.name.equals("Pouvoirs magiques")) {
            updateQualityPouvoirsMagiques(item);
        } else if (!item.name.equals("Kryptonite")) {
            updateNormalItemQuality(item);
        }

        if (!item.name.equals("Kryptonite")) {
            item.sellIn -= 1;
        }

        if (item.sellIn < 0) {
            updateItemQualityWhenSellInPassed(item);
        }
    }

    private void updateComteQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void updatePassVipConcertQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn < 11) {
                item.quality += 1;
            }
            if (item.sellIn < 6) {
                item.quality += 1;
            }
        }
    }

    private void updateNormalItemQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    private void updateQualityPouvoirsMagiques(Item item) {
        if (item.quality < 50) {
            item.quality -= 2;
        }
    }

    private void updateItemQualityWhenSellInPassed(Item item) {
        if (item.name.equals("Comté")) {
            updateComteQuality(item);
        } else if (item.name.equals("Pass VIP Concert")) {
            item.quality = 0;
        } else {
            updateNormalItemQuality(item);
        }
    }

    /* ***************LEGACY******************* */
    public void updateQuality3() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Comté")
                    && !items[i].name.equals("Pass VIP Concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Kryptonite")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Kryptonite")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Comté")) {
                    if (!items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Kryptonite")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
