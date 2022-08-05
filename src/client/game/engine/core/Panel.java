package client.game.engine.core;
import client.game.engine.GameContainer;
import client.game.nodes.tiles.Map1CollitionManager;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel {
    GameContainer container;
    Map1CollitionManager tileM;
    
    public Panel(GameContainer container, Window window) {
        this.container = container;
        this.setPreferredSize(new Dimension(window.getScreenWidth(), window.getScreenHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        container.draw(g2);

        g2.dispose();
    }
}
