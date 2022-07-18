package client.game.engine.core;
import client.game.engine.GameContainer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private GameContainer container;

    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;

    public Input(GameContainer container, Window window) {
        this.container = container;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        window.getWindow().addKeyListener(this);
        window.getWindow().addMouseMotionListener(this);
        window.getWindow().addMouseListener(this);
        window.getWindow().addMouseWheelListener(this);
    }

    public void update() {
        for(int i = 0; i< NUM_KEYS; i++) {
            keysLast[i] = keys[i];
        }
        for(int i = 0; i< NUM_BUTTONS; i++) {
            buttonsLast[i] = buttons[i];
        }
    }
    
    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }
    
    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }
    
    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }
    
    public boolean isButton(int button) {
        return buttons[button];
    }
    
    public boolean isButtonUp(int button) {
        return !buttons[button] && buttonsLast[button];
    }
    
    public boolean isButtonDown(int button) {
        return buttons[button] && !buttonsLast[button];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX() / container.getScale().getScale());
        mouseY = (int) (e.getY() / container.getScale().getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / container.getScale().getScale());
        mouseY = (int) (e.getY() / container.getScale().getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    // Getters of Mouse
    
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getScroll() {
        return scroll;
    }
}
