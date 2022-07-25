package client.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ImageUtils {
    
    public static BufferedImage flipXImage(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }
    
    public static BufferedImage[] flipXImageArray(BufferedImage[] images) {
        BufferedImage[] newArrayImages = new BufferedImage[images.length];
        for (int i = 0;i < images.length;i++) {
            newArrayImages[i] = ImageUtils.flipXImage(images[i]);
        }
        return newArrayImages;
    }
}

// Gracias a https://stackoverflow.com/questions/9558981/flip-image-with-graphics2d :)