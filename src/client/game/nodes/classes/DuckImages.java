package client.game.nodes.classes;

import client.utils.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DuckImages {
    public static BufferedImage[] movingLeft;
    public static BufferedImage[] movingRight;
    public static BufferedImage[] staticDuckLeft;
    public static BufferedImage[] staticDuckRight;
    public static BufferedImage[] spriteDead;
    public static BufferedImage[] spriteButton;
    public static BufferedImage[] spriteDying;
    public static BufferedImage[] spriteKillingLeft;
    public static BufferedImage[] spriteKillingRight;

    public static void load() {
        DuckImages instance = new DuckImages();
        instance.initPlayerImages();
    }

    private void initPlayerImages() {
        try {
            BufferedImage[] spriteDead = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dead/dead.png")),};
            DuckImages.spriteDead = spriteDead;

            BufferedImage[] spriteDying = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die1.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die2.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die3.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die4.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die5.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die6.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die7.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die8.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die9.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die10.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die11.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/dying/die11.png")),};
            DuckImages.spriteDying = spriteDying;

            BufferedImage[] spriteKillingLeft = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft1.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft2.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft3.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft4.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft5.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingLeft/killLeft5.png")),};
            DuckImages.spriteKillingLeft = spriteKillingLeft;

            BufferedImage[] spriteKillingRight = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight1.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight2.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight3.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight4.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight5.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/killingRight/killRight5.png")),};
            DuckImages.spriteKillingRight = spriteKillingRight;

            BufferedImage[] spriteButton = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButton.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButtonWait.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/buttons/killButtonNo.png"))
            };
            DuckImages.spriteButton = spriteButton;

            BufferedImage[] staticSpriteLeft = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png")),};
            DuckImages.staticDuckLeft = staticSpriteLeft;
            DuckImages.staticDuckRight = ImageUtils.flipXImageArray(DuckImages.staticDuckLeft);
            BufferedImage[] movingLeft = {
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak1.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak2.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak3.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak4.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak5.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak6.png")),
                ImageIO.read(getClass().getResourceAsStream("/client/resources/game/duck/walking/cuak7.png"))
            };
            DuckImages.movingLeft = movingLeft;
            DuckImages.movingRight = ImageUtils.flipXImageArray(DuckImages.movingLeft);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
