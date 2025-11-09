import javax.swing.SwingUtilities;

public class Game {
    public static void main(String[] args) {
        Player player = new Player("Player");
        player.bananas = 100000000000000.0;
        Building.initBuildingList();
        Upgrade.initUpgrades();
        SwingUtilities.invokeLater(() -> {new BananaClickerGUI(player).setVisible(true);});
    }
}
