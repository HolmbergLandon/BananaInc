import java.util.ArrayList;

public class Upgrade {
    public static ArrayList<Upgrade> upgrades = new ArrayList<>();
    public static ArrayList<Upgrade> shownUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> purchasedUpgrades = new ArrayList<>();
    public static ArrayList<Upgrade> hiddenUpgrades = new ArrayList<>();

    public String name;
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
     */
    public Upgrade(double bananasPerSecondMultiplier, int indexUpgraded, String name, double priceMultiplier, Sprite sprite, ArrayList<Upgrade> requiredUpgrades) {
        this.bananasPerSecondMultiplier = bananasPerSecondMultiplier;
        this.indexUpgraded = indexUpgraded;
        this.name = name;
        this.priceMultiplier = priceMultiplier;
        this.sprite = sprite;
        this.requiredUpgrades = requiredUpgrades;
    }

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
                
            }
        }

    }



}
