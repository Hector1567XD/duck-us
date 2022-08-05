package client.forms;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuWindow {
    private JFrame window;
    private JPanel panel;

    // SCREEN SETTINGS
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth; // 1024 pixels
    int screenHeight; // 768 pixels

    public MenuWindow() {
        this.screenWidth = 32 * this.maxScreenCol;
        this.screenHeight = 32 * this.maxScreenRow;

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Menu Juego");
    }

    public void setPanel(JPanel jPanel) {
        if (this.panel != null) {
            window.remove(this.panel);
        }
        window.add(jPanel);
        this.panel = jPanel;
        window.pack();
        window.setFocusable(true);
        window.setVisible(true);
    }

    public void open() {
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public void close() {
        window.setVisible(false);
        window.dispose();
    }

    public JFrame getWindow() {
        return window;
    }

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
