package MainGUI;


import java.awt.Image;
import java.awt.image.BufferedImage;

public class uTool {
    public static BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        scaledImage.getGraphics().drawImage(tmp, 0, 0, null);
        return scaledImage;
    }
}
