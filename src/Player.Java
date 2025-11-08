import java.util.ArrayList;
import java.util.Math;

public class Player {
    // All player variables
    public double bananas; 
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
    public int buildingBananas() {
        int numberBananasFromBuildings = 0;
        for (int i = 0; i < buildings.size(), i++) {
           numberBananasFromBuildings += Banana.getBananasPerSecond(Building.NUM_BUILDINGS, i)    
     }
        return numberBananasFromBuildings;
    }

}