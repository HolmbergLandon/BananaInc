import java.util.ArrayList;

public class Upgrade extends Purchaseable{
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    public static ArrayList<Upgrade> shownUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> hiddenUpgrades = new ArrayList<>();

    public String description;
    public int indexUpgraded;
    public double bananasPerSecondMultiplier;
    public double priceMultiplier;
    public Sprite sprite;
    public ArrayList<Upgrade> requiredUpgrades;

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
                ArrayList<Upgrade> requiredUpgrades, String description, int basePrice) {
        super(name, basePrice);
        this.bananasPerSecondMultiplier = bananasPerSecondMultiplier;
        this.indexUpgraded = indexUpgraded;
        this.priceMultiplier = priceMultiplier;
        this.sprite = sprite;
        this.requiredUpgrades = requiredUpgrades;
        this.description = description;
    }

    public static void initUpgrades() {
        upgrades.add(new Upgrade(2, 0, "Cursor Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of cursors", 20));
        upgrades.add(new Upgrade(2, 1, "Laborer Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of laborers", 200));
        upgrades.add(new Upgrade(2, 2, "Farm Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of laborers", 2000));
        upgrades.add(new Upgrade(2, 3, "Combine Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of laborers", 20000));
        upgrades.add(new Upgrade(2, 4, "Plantation Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of laborers", 200000));
        upgrades.add(new Upgrade(2, 5, "Alternate Universe Upgrade", 
            1, null, new ArrayList<>(), "Increases efficiency of laborers", 2000000));
        

        for(Upgrade u : upgrades) {
            hiddenUpgrades.add(u);
        }
        unhideUpgrades();
    }

    /**
     * Move an upgrade from the shown upgrades to the purchased. 
     * Then go through hidden upgrades to see if any more are purchaseable.
     * @param upgrade
     */
    public static void purchaseUpgrade(Upgrade upgrade) {
        shownUpgrades.remove(upgrade);
        purchasedUpgrades.add(upgrade);
        unhideUpgrades();
    }

    public static void unhideUpgrades() {
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

    public boolean isVisible() {
        return Upgrade.shownUpgrades.contains(this);
    }

    public boolean isPurchased() {
        return Upgrade.purchasedUpgrades.contains(this);
    }


}
