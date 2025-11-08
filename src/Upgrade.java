import java.util.ArrayList;

public class Upgrade {
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    public static ArrayList<Upgrade> shownUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> hiddenUpgrades = new ArrayList<>();

    public String name;
    public String description;
    public int indexUpgraded;
    public double bananasPerSecondMultiplier;
    public double priceMultiplier;
    public Sprite sprite;
    public ArrayList<Upgrade> requiredUpgrades;
    public int price;

    /**
     * Full arg constructor.
     * @param bananasPerSecondMultiplier The multiplier on the number of bananas per second.
     * @param indexUpgraded The index of the building to be upgraded in the buildingList.
     * @param name The name of the upgrade.
     * @param priceMultiplier The multiplier on the price fo the building to be upgraded.
     * @param sprite The sprite of the upgrade.
     * @param requiredUpgrades An arrayList of required buildings before you can get this upgrade.
     * @param description The description of the upgrade.
     */
    public Upgrade(double bananasPerSecondMultiplier, int indexUpgraded, String name, double priceMultiplier, Sprite sprite, 
                    ArrayList<Upgrade> requiredUpgrades, String description, int price) {
        this.bananasPerSecondMultiplier = bananasPerSecondMultiplier;
        this.indexUpgraded = indexUpgraded;
        this.name = name;
        this.priceMultiplier = priceMultiplier;
        this.sprite = sprite;
        this.requiredUpgrades = requiredUpgrades;
        this.description = description;
        this.price = price;
    }

    /**
     * Move an upgrade from the shown upgrades to the purchased. 
     * Then go through hidden upgrades to see if any more are purchaseable.
     * @param upgrade
     */
    public void purchaseUpgrade(Upgrade upgrade) {
        shownUpgrades.remove(upgrade);
        purchasedUpgrades.add(upgrade);
        for(int i = 0; i < hiddenUpgrades.size(); i++) {
            Upgrade hiddenUpgrade = hiddenUpgrades.get(i);
            boolean stayHidden = false;
            for(Upgrade u : hiddenUpgrade.requiredUpgrades) {
                if(!purchasedUpgrades.contains(u)) {
                    // Means that the upgrade cannot be purchased yet, keep hidden.
                    stayHidden = true;
                    break;
                }
            }
            if(!stayHidden) {
                shownUpgrades.add(hiddenUpgrade);
                hiddenUpgrades.remove(hiddenUpgrade);
                i--;
            }
        }
    }

    /**
     * Given a building index, go through upgrades and see the bananas per second modifier.
     * @param index The building index.
     * @return The banana multiplier.
     */
    public static double getBananaMultiplierForBuildingIndex(int index) {
        double modifier = 1;
        for(int i = 0; i < purchasedUpgrades.size(); i++) {
            Upgrade upgrade = purchasedUpgrades.get(i);
            if(upgrade.indexUpgraded == index) {
                modifier *= upgrade.bananasPerSecondMultiplier;
            }
        }
        return modifier;
    }

    /**
     * Given a building index, go through upgrades and see the price modifier.
     * @param index
     * @return
     */
    public static double getPriceMultiplierForBuildingIndex(int index) {
        double modifier = 1;
        for(int i = 0; i < purchasedUpgrades.size(); i++) {
            Upgrade upgrade = purchasedUpgrades.get(i);
            if(upgrade.indexUpgraded == index) {
                modifier *= upgrade.priceMultiplier;
            }
        }
        return modifier;
    }

    /**
     * Given a number of bananas, return whether or not the upgrade is purchaseable.
     * @param bananas The number of bananas that the user has.
     * @return Whether or not the upgrade can be purchased.
     */
    public boolean canPurchase(int bananas) {
        return bananas >= this.price;
    }

}
