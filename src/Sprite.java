import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Sprite {
    private static Map<String, Image> images = new HashMap<>();
    
    static {
        loadImage("bananaTier1", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/fourBanana.png");
        loadImage("bananaTier2", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/twoBanana.png");
        loadImage("bananaTier3", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/threeBanana.png");
        loadImage("bananaTier4", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/fourBanana.png");
        loadImage("backgroundTier1", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/tier1Background.png");
        loadImage("backgroundTier2", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/tier2Background.png");
        loadImage("backgroundTier3", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/tier3Background.png");
        loadImage("backgroundTier4", "/Users/landonholmberg/Library/CloudStorage/OneDrive-UniversityofNebraska-Lincoln/CornHacks/BananaInc/sprites/tier4Background.png");
    }
    
    /**
     * Get banana image based on player's rebirth tier
     */
    public static Image getBananaImage(int rebirthTier) {
        // Ensure tier is within valid range (1-4)
        return images.get("bananaTier" + rebirthTier);
    }
    
    /**
     * Get background image based on player's rebirth tier
     */
    public static Image getBackgroundImage(int rebirthTier) {
        // Ensure tier is within valid range (1-4)
        return images.get("backgroundTier" + rebirthTier);
    }

    public static Image getBananaImage() {
        return images.get("banana");
    }
    
    public static Image getUpgradeImage(String upgradeName) {
        String key = upgradeName.toLowerCase();
        return images.get(key);
    }
    
    public static Image getBackgroundImage() {
        return images.get("background");
    }
    
    /**
     * Method to load images
     * @param key
     * @param imagePath
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