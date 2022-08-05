package client.game.utils;

import client.game.engine.GameContainer;
import client.game.engine.nodos.AbstractCamera;
import client.utils.game.collitions.CollideBox;
import client.utils.game.collitions.CollitionsUtils;
import java.awt.Graphics2D;

public class CollideBoxCamDrawer {
    public static void drawCollideBox(GameContainer container, Graphics2D g2, CollideBox collideBox) {
        int scale = (int) container.getScale().getScale();
        AbstractCamera camera = container.getController().getCamera();
        if (camera != null) {
            CollitionsUtils.drawCollideBox(g2, collideBox, scale, camera.getDeltaCameraX(), camera.getDeltaCameraY());
        }else{
            CollitionsUtils.drawCollideBox(g2, collideBox, scale);
        }
    }
}
