
public class Player {
    // All player variables
    public double bananas; 
    public double bananaPerClick = 1;
    public double bananasPerSecond = 0;
    public String username = ""; 
    public int timesRebirthed;
    public int rebirthTier = checkRebirthTier();
    
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

    /**
     * This function checks number of rebirths and returns the tier
     */
    private void checkRebirthTier() {
        if (this.timesRebirthed < 10) {
            rebirthTier = 1;
        } else if (this.timesRebirthed < 50) {
            rebirthTier = 2;
        } else if (this.rebirthTier < 100) {
            rebirthTier = 3;
        } else {
            rebirthTier = 4;
        }
    }
}