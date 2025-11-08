import java.util.ArrayList;
import java.util.Math;

public class Player {
    // All player variables
    public double bananas; 
    public double playerBananaPerClick = 1;
    public double bananaPerSecond = 0;
    public final String USER_NAME = ""; 
    public int timesRebirthed; 
    public ArrayList<Integer>() buildings = new ArrayList<>();
    
    // Player constructor passing only username
    public player(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }
   
    /** Getting the number of bananas from buildings 
     * @return numberBananasFromBuildings
     */
    public int numBuildingsTotal() {
        int numberBananasFromBuildings = 0;
        for (int i = 0; i < buildings.size(), i++) {
           numberBananasFromBuildings += Banana.getBananasPerSecond(Building.NUM_BUILDINGS, i)    
     }
        return numberBananasFromBuildings;
    }

    /**
     * @param numAcquiredUpgrades number of each upgrade player has bought
     * @param numNeedNextTier how many buildings are needed per tier
     * @param baseBananPerUpgrade is how many bananas each upgrade gives
     * Updates bananaPerSecond to the correct number of bananas per second based on upgrade
     */
    public void bananasBasedOnUpgrade(int numAcquiredUpgrades, int numNeedNextTier, int baseBananaPerUpgrade) {
        int currentTier = numAcquiredUpgrades / numNeedNextTier;
        int eachUpgradeWorth = Math.pow(baseBananaPerUpgrade, currentTier);
        bananaPerSecond += eachUpgradeWorth * numAcquiredUpgrades;
    }

    public void playerClickBananaAmount(int numCursorUpgrades, int numNeedNextTierCursor) {
        int currentTier = numCursorUpgrades / numNeedNextTier;
        playerBananaPerClick = currentTier * numCursorUpgrades;
    }
}