package client.game.engine.core;
import client.game.engine.GameContainer;
import javax.swing.JFrame;

public class Window {
    private JFrame window;
    private Panel panel;
    private GameContainer container;

    // SCREEN SETTINGS
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth; // 1024 pixels
    int screenHeight; // 768 pixels

    public Window(GameContainer container) {
        this.container = container;
        this.screenWidth = container.getScale().getTileSize() * this.maxScreenCol;
        this.screenHeight = container.getScale().getTileSize() * this.maxScreenRow;

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle(container.gameName);
        
        this.panel = new Panel(container, this);
        window.add(this.panel);
        window.pack();
        window.setFocusable(true);
    }

    public void open() {
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public JFrame getWindow() {
        return window;
    }

    public Panel getPanel() {
        return this.panel;
    }
    
    // GETTERS

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    
}
