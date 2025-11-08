public abstract class Purchaseable {
    public String name;
    public int price;
    public int basePrice;
    public int count;
    public static final double PURCHASE_PRICE_MULTIPLIER = 2;

    public Purchaseable(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.price = basePrice;
        this.count = 0;
    }

    /**
     * Given a number of bananas, return whether or not the building is purchaseable.
     * @param bananas The number of bananas that the user has.
     * @return Whether or not the building can be purchased.
     */
    public boolean isPurchaseable(int bananas) {
        System.out.println("Number of bananas: " + bananas + ". Price: " + price);
        return bananas >= this.price;
    }

    /**
     * Given a number of bananas, attempt to purchase an upgrade.
     * @param bananas The number of bananas the user has. 
     * @return An Object[] of length 2.
     *              The first entry is true if the building was successfully purchased, false if not.
     *              The second entry is the new number of bananas.
     */
    public Object[] purchase(int bananas) {
        if(!isPurchaseable(bananas)) {
            return new Object[]{false, bananas};
        }
        int newBananas = bananas - this.price;
        this.count++;
        this.calculateNewPrice();
        return new Object[]{true, newBananas};
    }

    public void calculateNewPrice() {
        this.price *= PURCHASE_PRICE_MULTIPLIER;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
