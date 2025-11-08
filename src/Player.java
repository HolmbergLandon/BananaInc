
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
    public double numBuildingsTotal() {
        double numberBananasFromBuildings = 0;
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
        this.bananasPerSecond += eachUpgradeWorth * numAcquiredUpgrades;
    }

    public void calculateBananasPerSecond() {
        double bps = this.numBuildingsTotal();

        this.bananasPerSecond = bps;
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
    private int checkRebirthTier() {
        if (this.timesRebirthed < 10) {
            return 1;
        } else if (this.timesRebirthed < 50) {
            return 2;
        } else if (this.rebirthTier < 100) {
            return 3;
        } else {
            return 4;
        }
    }

    public void attemptPurchase(Building building) {
        Object[] status = building.purchase((int) this.bananas);
        if((Boolean) status[0] == false) {
            return;
        }
        this.bananas = (double) ((Integer) status[1]);
        this.calculateBananasPerSecond();
    }
}
