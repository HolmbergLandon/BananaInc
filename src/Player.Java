
public class Player {
    // All player variables
    public double bananas; 
    public double bananaPerClick = 1;
    public double bananasPerSecond = 0;
    public String username = ""; 
    public int timesRebirthed; 
    
    // Player constructor passing only username
    public Player(String username){
        this.username = username;
    }
   
    /** Getting the number of bananas from buildings 
     * @return numberBananasFromBuildings
     */
    public int numBuildingsTotal() {
        int numberBananasFromBuildings = 0;
        for (int i = 0; i < Building.buildingList.size(); i++) {
            numberBananasFromBuildings += Building.getBananasPerSecond(Building.NUM_BUILDINGS, i);    
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
        double eachUpgradeWorth = Math.pow(baseBananaPerUpgrade, currentTier);
        bananasPerSecond += eachUpgradeWorth * numAcquiredUpgrades;
    }

    public void playerClickBananaAmount(int numCursorUpgrades, int numNeedNextTierCursor) {
        int currentTier = numCursorUpgrades / numNeedNextTierCursor;
        bananaPerClick = currentTier * numCursorUpgrades;
    }

    public void clickBanana() {
        this.bananas += this.bananaPerClick;
    }
}