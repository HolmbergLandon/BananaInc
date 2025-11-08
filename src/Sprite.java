import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Sprite {
    private static Map<String, Image> images = new HashMap<>();
    
    static {
        // Load tier-based images
        loadImage("bananaTier1", "./BananaInc/sprites/oneBanana.png");
        loadImage("bananaTier2", "./BananaInc/sprites/twoBanana.png");
        loadImage("bananaTier3", "./BananaInc/sprites/threeBanana.png");
        loadImage("bananaTier4", "./BananaInc/sprites/fourBanana.png");
        loadImage("backgroundTier1", "./BananaInc/sprites/tier1Background.png");
        loadImage("backgroundTier2", "./BananaInc/sprites/tier2Background.png");
        loadImage("backgroundTier3", "./BananaInc/sprites/tier3Background.png");
        loadImage("backgroundTier4", "./BananaInc/sprites/tier4Background.png");
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
        String key = upgradeName.toLowerCase();
        return images.get(key);
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