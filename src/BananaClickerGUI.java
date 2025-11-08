import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


public class BananaClickerGUI extends JFrame {
    Player player;
    
    // Game components
    private JLabel bananaCountLabel;
    private JLabel bpsLabel;
    private JButton bananaButton;
    private JPanel upgradesPanel;
    private JPanel shopPanel;
    
    // Upgrade items
    
    public BananaClickerGUI(Player player) {
        this.player = player;
        initializeGame();
        setupGUI();
        startGameLoop();
    }
    
    private void initializeGame() {
        setTitle("Banana Clicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 222, 179)); // Banana cream background
        
        // Apply custom look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setupGUI() {
        createHeaderPanel();
        createBananaButton();
        createShopPanel();
        createUpgradesPanel();
        
        pack();
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(139, 69, 19)); // Brown background
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Banana count
        bananaCountLabel = new JLabel("Bananas: 0", SwingConstants.LEFT);
        bananaCountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bananaCountLabel.setForeground(Color.YELLOW);
        
        // Bananas per second
        bpsLabel = new JLabel("0 bananas/second", SwingConstants.RIGHT);
        bpsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bpsLabel.setForeground(Color.WHITE);
        
        headerPanel.add(bananaCountLabel, BorderLayout.WEST);
        headerPanel.add(bpsLabel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
    }
    
    private void createBananaButton() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 222, 179));
        
        bananaButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw banana image - replace with your Sprite class image
                Image bananaImage = Sprite.getBananaImage();
                if (bananaImage != null) {
                    g2d.drawImage(bananaImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Fallback drawing
                    g2d.setColor(Color.YELLOW);
                    g2d.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
                    g2d.setColor(new Color(139, 69, 19));
                    g2d.fillArc(getWidth()/2 - 20, 15, 40, 30, 0, 180);
                }
            }
        };
        
        bananaButton.setPreferredSize(new Dimension(300, 300));
        bananaButton.setBorder(BorderFactory.createEmptyBorder());
        bananaButton.setContentAreaFilled(false);
        bananaButton.setFocusPainted(false);
    
        
        // Add hover effect
        bananaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bananaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        centerPanel.add(bananaButton);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private void createShopPanel() {
        shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));
        shopPanel.setBackground(new Color(210, 180, 140)); // Tan background
        shopPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19), 2),
            "Banana Shop"
        ));
        
        JScrollPane scrollPane = new JScrollPane(shopPanel);
        scrollPane.setPreferredSize(new Dimension(300, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        System.out.println(Building.buildingList);
        // Create shop items
        for (Building building : Building.buildingList) {
            System.out.println(building);
            shopPanel.add(createShopItemPanel(building));
            shopPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        add(scrollPane, BorderLayout.EAST);
    }
    
    private JPanel createShopItemPanel(Building building) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(new Color(245, 222, 179));
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        itemPanel.setMaximumSize(new Dimension(280, 80));
        
        // Item icon
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image icon = Sprite.getUpgradeImage(building.name);
                if (icon != null) {
                    g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Fallback icon
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.DARK_GRAY);
                    g.drawString(building.name.substring(0, 1), getWidth() / 2 - 5, getHeight()/ 2 + 5);
                }
            }
        };
        iconLabel.setPreferredSize(new Dimension(50, 50));
        
        // Item info
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setBackground(new Color(245, 222, 179));
        
        JLabel nameLabel = new JLabel(building.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel costLabel = new JLabel("Cost: " + formatNumber(building.price) + " bananas");
        costLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel effectLabel = new JLabel("+" + building.bananasPerSecond + " bananas/sec");
        effectLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        infoPanel.add(nameLabel);
        infoPanel.add(costLabel);
        infoPanel.add(effectLabel);
        
        // Buy button
        JButton buyButton = new JButton("Buy");
        buyButton.setBackground(new Color(34, 139, 34)); // Forest green
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);
        buyButton.setPreferredSize(new Dimension(60, 30));
        
        itemPanel.add(iconLabel, BorderLayout.WEST);
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(buyButton, BorderLayout.EAST);
        
        return itemPanel;
    }
    
    private void createUpgradesPanel() {
        upgradesPanel = new JPanel(new FlowLayout());
        upgradesPanel.setBackground(new Color(210, 180, 140));
        upgradesPanel.setBorder(BorderFactory.createTitledBorder("Active Upgrades"));
        upgradesPanel.setPreferredSize(new Dimension(0, 100));
        
        add(upgradesPanel, BorderLayout.SOUTH);
    }
    
    private void updateShopItems() {
        Component[] components = shopPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel itemPanel = (JPanel) comp;
                Component[] itemComps = itemPanel.getComponents();
                for (Component itemComp : itemComps) {
                    if (itemComp instanceof JPanel) {
                        // Update cost labels
                        JPanel infoPanel = (JPanel) itemComp;
                        Component[] infoComps = infoPanel.getComponents();
                        if (infoComps.length >= 2 && infoComps[1] instanceof JLabel) {
                            JLabel costLabel = (JLabel) infoComps[1];
                            // Extract upgrade name from the first label
                            JLabel nameLabel = (JLabel) infoComps[0];
                            String upgradeName = nameLabel.getText();
                            
                            // Find the upgrade and update cost
                            for (Building building : Building.buildingList) {
                                if (building.name.equals(upgradeName)) {
                                    costLabel.setText("Cost: " + formatNumber(building.price) + " bananas");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void updateDisplay() {
        bananaCountLabel.setText("Bananas: " + formatNumber((int)player.bananas));
        bpsLabel.setText(formatNumber((int)player.bananasPerSecond) + " bananas/second");
    }
    
    private void animateClick() {
        Timer timer = new Timer(100, new ActionListener() {
            float scale = 1.0f;
            int count = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 5) {
                    scale = (count % 2 == 0) ? 0.9f : 1.1f;
                    bananaButton.setSize(
                        (int)(300 * scale), 
                        (int)(300 * scale)
                    );
                    count++;
                } else {
                    bananaButton.setSize(300, 300);
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    private void startGameLoop() {
        Timer gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.bananas += player.bananasPerSecond;
                updateDisplay();
            }
        });
        gameTimer.start();
    }
    
    private String formatNumber(int number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 1000000) {
            return String.format("%.1fK", number / 1000.0);
        } else {
            return String.format("%.1fM", number / 1000000.0);
        }
    }
}