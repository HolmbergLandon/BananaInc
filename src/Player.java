
public class Player {
    // All player variables
    static double INITIAL_REBIRTH_COST = 1000000;
    public double rebirthMultiplier = 1;
    public double bananas; 
    public double bananaPerClick;
    public double bananasPerSecond;
    public String username; 
    public int timesRebirthed;
    public double RebirthCost = getRebirthCost();
    public int rebirthTier = checkRebirthTier();
    
    // Player constructor passing only username
    public Player(String username){
        this.username = username;
        this.bananasPerSecond = 0;
        this.bananaPerClick = 1;
    }
   
    public void setBananasPerSecond() {
        this.bananasPerSecond = 0;
        for (Building b : Building.buildingList) {
            this.bananasPerSecond += b.getBananasPerSecond();
        }
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

    public boolean attemptPurchase(Purchaseable purchaseable) {
        Object[] status = purchaseable.purchase((int) this.bananas);
        if(!((Boolean) status[0])) {
            return false;
        }
        this.bananas = (double) ((Integer) status[1]);
        Upgrade.unhideUpgrades();
        this.setBananasPerSecond();
        return true;
    }

    public double getRebirthCost() {
        if (timesRebirthed > 1){
            return Math.pow(1.5, timesRebirthed) * INITIAL_REBIRTH_COST;
        }
        return INITIAL_REBIRTH_COST;
    }

    public void setRebirthModifier() {
        this.rebirthMultiplier = Math.pow(1.1, timesRebirthed) * rebirthMultiplier;
    }
}
