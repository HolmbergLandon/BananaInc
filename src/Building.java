import java.util.ArrayList;

public class Building {
    public String name;
    public int price;
    public double bananasPerSecond;
    
    
    public final double priceMultiplier = 1.5;


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
        this.name = name;
        this.price = basePrice;
        this.bananasPerSecond = bananasPerSecond;
    }

    public void initBuildingList() {
        buildingList.add(new Building("Cursor", 10, 0.1));
        buildingList.add(new Building("Laborer", 100, 2));
    }

    /**
     * Given a number of buildings and the index in buildingList, return the number of bananas per seconed
     * @param numBuildings The number of buildings of the given type.
     * @param index The index within buildingList of the building.
     * @return The number of bananas per second.
     */
    public double getBananasPerSecond(final int numBuildings, final int index) {
        return buildingList.get(index).bananasPerSecond;
    }

}
