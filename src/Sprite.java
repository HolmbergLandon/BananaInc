import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Sprite {
    private static Map<String, Image> images = new HashMap<>();
    
    
    static {
        if(System.getProperty("os.name").toLowerCase().contains("win")) {
            loadImage("bananaTier1", "./BananaInc/sprites/oneBanana.png");
            loadImage("bananaTier2", "./BananaInc/sprites/twoBanana.png");
            loadImage("bananaTier3", "./BananaInc/sprites/threeBanana.png");
            loadImage("bananaTier4", "./BananaInc/sprites/fourBanana.png");
            loadImage("backgroundTier1", "./BananaInc/sprites/tier1Background.png");
            loadImage("backgroundTier2", "./BananaInc/sprites/tier2Background.png");
            loadImage("backgroundTier3", "./BananaInc/sprites/tier3Background.png");
            loadImage("backgroundTier4", "./BananaInc/sprites/tier4Background.png");
            loadImage("Cursor", "./BananaInc/sprites/cursor.png");
            loadImage("Laborer", "./BananaInc/sprites/laborer.png");
            loadImage("Farm", "./BananaInc/sprites/farm.png");
            loadImage("Crawler", "./BananaInc/sprites/crawler.png");
            loadImage("Plantation", "./BananaInc/sprites/plantation.png");
            loadImage("GMO Factory", "./BananaInc/sprites/GMOFactory.png");
            loadImage("Alternate Universe", "./BananaInc/sprites/alternateUniverse.png");
        } else {
            loadImage("bananaTier1", "sprites/oneBanana.png");
            loadImage("bananaTier2", "sprites/twoBanana.png");
            loadImage("bananaTier3", "sprites/threeBanana.png");
            loadImage("bananaTier4", "sprites/fourBanana.png");
            loadImage("backgroundTier1", "sprites/tier1Background.png");
            loadImage("backgroundTier2", "sprites/tier2Background.png");
            loadImage("backgroundTier3", "sprites/tier3Background.png");
            loadImage("backgroundTier4", "sprites/tier4Background.png");
            loadImage("Cursor", "sprites/cursor.png");
            loadImage("Laborer", "sprites/laborer.png");
            loadImage("Farm", "sprites/farm.png");
            loadImage("Crawler", "sprites/crawler.png");
            loadImage("Plantation", "sprites/plantation.png");
            loadImage("GMO Factory", "sprites/GMOFactory.png");
            loadImage("Alternate Universe", "sprites/alternateUniverse.png");
        }
        // Load tier-based images
        
    }
    
    /**
     * Get banana image based on player's rebirth tier
     */
    public static Image getBananaImage(int rebirthTier) {
        // Ensure tier is within valid range (1-4)
        int actualTier = Math.max(1, Math.min(rebirthTier, 4));
        return images.get("bananaTier" + actualTier);
    }
    
    /**
     * Get background image based on player's rebirth tier
     */
    public static Image getBackgroundImage(int rebirthTier) {
        // Ensure tier is within valid range (1-4)
        int actualTier = Math.max(1, Math.min(rebirthTier, 4));
        return images.get("backgroundTier" + actualTier);
    }

    /**
     * Default banana image (tier 1)
     */
    public static Image getBananaImage() {
        return getBananaImage(1);
    }
    
    public static Image getUpgradeImage(String upgradeName) {
        return images.get(upgradeName);
    }
    
    /**
     * Method to load images
     */
    public static void loadImage(String key, String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            images.put(key, icon.getImage());
        } catch (Exception e) {
            System.err.println("Could not load image: " + imagePath);
        }
    }
}