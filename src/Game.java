import javax.swing.SwingUtilities;

public class Game {
    public static void main(String[] args) {
        Player player = new Player("Player");
        Building.initBuildingList();
        Upgrade.initUpgrades();
        SwingUtilities.invokeLater(() -> {new BananaClickerGUI(player).setVisible(true);});
    }
}
