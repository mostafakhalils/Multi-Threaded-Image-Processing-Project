import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFilters {
    public static BufferedImage resizeImage(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static BufferedImage applyGrayscale(BufferedImage img) {
        BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = output.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return output;
    }

    public static BufferedImage applyBinary(BufferedImage img) {
        BufferedImage binary = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = binary.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return binary;
    }

    public static BufferedImage applyNegative(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y));
                Color nc = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
                result.setRGB(x, y, nc.getRGB());
            }
        }
        return result;
    }

    public static BufferedImage applyLogTransform(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        double c = 255 / Math.log(1 + 255);
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color color = new Color(img.getRGB(x, y));
                int r = (int) (c * Math.log(1 + color.getRed()));
                int g = (int) (c * Math.log(1 + color.getGreen()));
                int b = (int) (c * Math.log(1 + color.getBlue()));
                result.setRGB(x, y, new Color(clamp(r), clamp(g), clamp(b)).getRGB());
            }
        }
        return result;
    }

    public static BufferedImage applyPowerLawTransform(BufferedImage img, double gamma) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        double c = 1.0;
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color color = new Color(img.getRGB(x, y));
                int r = (int) (c * Math.pow(color.getRed() / 255.0, gamma) * 255);
                int g = (int) (c * Math.pow(color.getGreen() / 255.0, gamma) * 255);
                int b = (int) (c * Math.pow(color.getBlue() / 255.0, gamma) * 255);
                result.setRGB(x, y, new Color(clamp(r), clamp(g), clamp(b)).getRGB());
            }
        }
        return result;
    }

    private static int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }
}
