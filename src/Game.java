import javax.swing.SwingUtilities;

public class Game {
    public static void main(String[] args) {
        Player player = new Player("Player");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BananaClickerGUI(player).setVisible(true);
            }
        });
    }
}
