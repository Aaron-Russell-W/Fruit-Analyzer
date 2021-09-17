package Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
public class Controller implements Initializable {
    @FXML
    ImageView original, blackWhite, boxImage;
    @FXML
    MenuItem imageChoice;
    @FXML
    Slider hueSlider;
    @FXML
    Circle colorCircle;
    ImageManipulation imgManip;
    PixelArray pixelArray;
    UnionFind unionFind;


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Event handler when entering image into Javafx
     * @param event
     * @throws IOException
     */
    public void imgChoose(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose image file");
        File file = fileChooser.showOpenDialog(imageChoice.getParentPopup().getOwnerWindow());
        Image image = new Image(file.toURI().toString(), 512, 512, false, false);
        imgManip = new ImageManipulation(image);
        original.setImage(imgManip.getImage());
        blackWhite.setImage(imgManip.getImage());
        boxImage.setImage(imgManip.getImage());

    }

    /**
     * 'Update' button that selects the hue of the image for conversion to black and white
     *
     * @param event
     */
    public void selection(ActionEvent event) {
        Color color = Color.hsb(hueSlider.getValue(), 1.0, 1.0);
        colorCircle.setFill(color);
        imgManip.blackWhiteConversion(hueSlider.getValue());
        blackWhite.setImage(imgManip.getBlackWhiteImg());
    }

    /**
     * 'Construct button that enters the pixels of the image into an array, unions them accordingly with down and right,
     * constructs the boxes and visualises these boxes on a new tab.
     *
     * @param e
     */
    public void constructBoxes(ActionEvent e) {
        pixelArray = new PixelArray();
        UnionFind unionFind = new UnionFind();
        pixelArray.enterPixelsIntoArray(imgManip.getBlackWhiteImg());
        pixelArray.setImageArray(unionFind.unionRightAndDown(pixelArray.getImageArray(), blackWhite.getImage()));
        pixelArray.moveArrayIntoHashMap();
        pixelArray.removeKeysUnderCertainSize(100);
        boxImage.setImage(imgManip.getImage());
        boxTime(pixelArray.getHashMap());
        pixelArray.reportNumberOfPixelsInEachSet();

    }

    /**
     * Event handler for selecting random colors for every different fruit
     * @param e
     */
    public void randColor(ActionEvent e) {
        boxImage.setImage(imgManip.randomColorUniqueSets(pixelArray.getHashMap(), boxImage.getImage()));
    }

    /**
     * Simple image to writable image conversion
     *
     * @param image image to change to writable image
     * @return WritableImage that matches the original image
     */
    public WritableImage imageToWritable(Image image) {
        PixelReader pixelReader = image.getPixelReader();
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        for (int c = 0; c < image.getWidth(); c++) {
            for (int r = 0; r < image.getHeight(); r++) {
                Color color = pixelReader.getColor(c, r);
                pixelWriter.setColor(c, r, color);
            }
        }
        return wImage;
    }


    /**
     * gets smallest and biggest x's and y's for every hashmap key to enter into a method to construct the rectangles
     * @param hashMap hashMap from PixelArray
     */
    public void boxTime(HashMap<Integer, ArrayList<Integer>> hashMap) {
        double smallX, smallY, maxX, maxY;
        double imageWidth = boxImage.getFitWidth();
        for (Integer key : hashMap.keySet()) {
            smallX = (hashMap.get(key).get(0)) % imageWidth;
            smallY = (hashMap.get(key).get(0)) / imageWidth;
            maxX = (hashMap.get(key).get(0)) % imageWidth;
            maxY = (hashMap.get(key).get(0)) / imageWidth;
            for (int i = 0; i < hashMap.get(key).size(); i++) {
                double currentX = hashMap.get(key).get(i) % imageWidth;

                double currentY = hashMap.get(key).get(i) / imageWidth;

                if (currentX < smallX) {
                    smallX = currentX;
                }
                if (currentX > maxX) {
                    maxX = currentX;
                }
                if ((smallY != 0) && (currentY < smallY)) {
                    smallY = currentY;
                }
                if (currentY > maxY) {
                    maxY = currentY;
                }
            }

            constructRects(smallX, smallY, maxX, maxY, key);

        }
    }

    /**
     * Used in boxTime() to construct the rect
     * @param smallX smallest x from each keyset.
     * @param smallY smallest y from each keyset.
     * @param maxX biggest x from each keyset.
     * @param maxY biggest y from each keyset.
     * @param key for each hashset
     */
    public void constructRects(double smallX, double smallY, double maxX, double maxY, int key) {
        double width = maxX - smallX;
        double height = maxY - smallY;
        Rectangle rect = new Rectangle();
        rect.setStroke((Color.BLUE));
        rect.setHeight(height);
        rect.setWidth(width);
        rect.setX(smallX);
        rect.setY(smallY);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLUE);
        Tooltip toolTip = new Tooltip("Root = " + key + " Size in Pixel Units: " + height * width);
        Tooltip.install(rect, toolTip);
        ((Pane) boxImage.getParent()).getChildren().add(rect);
    }
}
