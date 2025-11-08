import java.util.ArrayList;

public class Building extends Purchaseable {
    public double bananasPerSecond;

    public static final int NUM_BUILDINGS = 2;
    
    // List of the possible buildings that the player can buy
    public static ArrayList<Building> buildingList = new ArrayList<>(NUM_BUILDINGS);


    /**
     * Full arg constructor.
     * @param name Name of the building.
     * @param basePrice Base price of the building.
     * @param bananasPerSecond Bananas per second.
     */    
    public Building(String name, int basePrice, double bananasPerSecond) {
        super(name, basePrice);
        this.bananasPerSecond = bananasPerSecond;
    }

    public static void initBuildingList() {
        buildingList.add(new Building("Cursor", 10, 0.1));
        buildingList.add(new Building("Laborer", 100, 2));
        buildingList.add(new Building("Farm", 1000, 50));
        buildingList.add(new Building("Combine", 10000, 250));
        buildingList.add(new Building("GMO Factory", 100000, 1200));
        buildingList.add(new Building("Plantation", 100000, 1200));
        buildingList.add(new Building("Alternate Universe", 100000, 1200));
    }

    /**
     * Given a number of buildings and the index in buildingList, return the number of bananas per seconed
     * @param numBuildings The number of buildings of the given type.
     * @param index The index within buildingList of the building.
     * @return The number of bananas per second.
     */
    public double getBananasPerSecond() {
        double val = this.bananasPerSecond * this.count * Upgrade.getBananaMultiplierForBuildingIndex(this.getIndexInBuildingList());
        val *= Math.pow(2, this.count / 50);
        // System.out.println(val);
        return val;
    }


    public int getIndexInBuildingList() {
        return buildingList.indexOf(this);
    }

    @Override
    public void calculateNewPrice() {
        double individualMultiplier = Upgrade.getPriceMultiplierForBuildingIndex(this.getIndexInBuildingList());
        this.price = (int) (individualMultiplier * basePrice * Math.pow(Purchaseable.PURCHASE_PRICE_MULTIPLIER, count));
    }

}
