package Application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageManipulation {
    private Image image;
    private WritableImage blackWhiteImg;

    public ImageManipulation(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Simple black white conversion where fruit is converted to white and any other pixel to black
     * @param hue hue from slider.
     */
    public void blackWhiteConversion(Double hue) {
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        Color blackColor = new Color(0, 0, 0, 1);
        Color whiteColor = new Color(1, 1, 1, 1);
        for (int c = 0; c < image.getWidth(); c++)
            for (int r = 0; r < image.getHeight(); r++) {
                Color color = pixelReader.getColor(c, r);
                if ((color.getHue() > hue + 25) || (color.getHue() < hue - 25)) {
                    pixelWriter.setColor(c, r, blackColor);
                } else {
                    pixelWriter.setColor(c, r, whiteColor);
                }
            }
        setBlackWhiteImg(wImage);
    }

    /**
     * random colors each individual instance of fruit
     * @param hashMap hashmap from pixelArray
     * @param image user selected image
     * @return writable image randomly colored sets
     */
    public WritableImage randomColorUniqueSets(HashMap<Integer, ArrayList<Integer>> hashMap, Image image) {
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();
        double hue = 0;
        for (int c = 0; c < image.getWidth(); c++) {
            for (int r = 0; r < image.getWidth(); r++) {
                pixelWriter.setColor(c, r, Color.BLACK);
            }
        }
        for (int key : hashMap.keySet()) {
            hue = hue + 30;
            if (hue > 360) {
                hue = 0;
            }
            for (int i = 0; i < hashMap.get(key).size(); i++) {
                int curX = hashMap.get(key).get(i) % (int) image.getWidth();
                int curY = hashMap.get(key).get(i) / (int) image.getWidth();
                Color color = Color.hsb(hue, 1, 1);
                pixelWriter.setColor(curX, curY, color);
            }

        }
        return wImage;
    }

    public WritableImage getBlackWhiteImg() {
        return blackWhiteImg;
    }

    public void setBlackWhiteImg(WritableImage blackWhiteImg) {
        this.blackWhiteImg = blackWhiteImg;
    }
}
