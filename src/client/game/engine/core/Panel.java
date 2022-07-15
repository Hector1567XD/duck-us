package client.game.engine.core;
import client.game.engine.GameContainer;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Panel extends JPanel {
    GameContainer container;

    public Panel(GameContainer container, Window window) {
        this.container = container;
        this.setPreferredSize(new Dimension(window.getScreenWidth(), window.getScreenHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) { //le pasamos por parametro una variable de tipo graphics
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //Convertimos el graphics a 2D
        container.draw(g2);

        g2.dispose();
    }
}
