
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
    }

    static {
        // Initialize your images here
        // For now, we'll use null - replace these with actual image loading
        images.put("bananaRebirthTier1", null);
        images.put("grandma", null);
        images.put("farm", null);
        images.put("factory", null);
        images.put("mine", null);
        images.put("shipment", null);
        images.put("portal", null);
        images.put("background", null);
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
