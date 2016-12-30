package experiment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by bill33 on 2016/9/24.
 */
public class ResizeImage {
    private static int IMG_WIDTH = 100;
    private static int IMG_HEIGHT = 100;

    public static void main(String[] args) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("/Users/eddard33/Pictures/bg01.png"));
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
        ImageIO.write(resizeImageJpg, "png", new File("/Users/eddard33/Pictures/test3.jpeg"));

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        System.out.println(type);
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizedImage;
    }
}
