package Application;

/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
*/
public class WorkingSmelly {}
    /*
    package Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;


import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        Image imageChosen;
        int[] imageArray;


        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

        public void imgChoose(ActionEvent event) throws IOException {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose image file");
            File file = fileChooser.showOpenDialog(imageChoice.getParentPopup().getOwnerWindow());
            Image image = new Image(file.toURI().toString(), 512, 512, false, false);
            imageChosen = image;

            original.setImage(image);
            System.out.println("Width = " + image.getWidth() + "Height =" + image.getHeight());
            System.out.println("Width = " + original.getImage().getWidth() + "Height = " + original.getImage().getHeight());
            blackWhite.setImage(image);


        }

     */

        /*
         * 'Update' button that selects the hue of the image for conversion to black and white
         *
         * @param event
         */
/*
        public void selection(ActionEvent event) {
            Color color = Color.hsb(hueSlider.getValue(), 1.0, 1.0);
            colorCircle.setFill(color);
            blackWhite.setImage(blackWhiteConversion(imageChosen, hueSlider.getValue()));
            System.out.println(hueSlider.getValue());
        }
*/
        /*
         * 'Construct button that enters the pixels of the image into an array, unions them accordingly with down and right,
         * constructs the boxes and visualises these boxes on a new tab.
         *
         * @param e
         */
        /*
        public void constructBoxes(ActionEvent e) {
            imageArray = enterPixelsIntoArray(blackWhite.getImage());
            imageArray = unionRightAndDown(imageArray, blackWhite.getImage());
            imageArray = unionRightAndDown(imageArray, blackWhite.getImage());
            moveArrayIntoHashMap();
            for (Integer key : hashMap.keySet()) {
                System.out.println("Key = " + key);
            }
            removeKeysUnderCertainLengthInHashMap(100);
            for (Integer key : hashMap.keySet()) {
                System.out.println("rKey = " + key + " 1st Value = " + hashMap.get(key).get(0));
            }
            WritableImage wImage = imageToWritable(imageChosen);
            boxImage.setImage(imageChosen);
            boxTime();

        }

        /**
         * Simple image to writable image conversion
         *
         * @param image image to change to writable image
         * @return WritableImage that matches the original image
         */
        /*
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

*/
        /*
         * @param image user selected image of fruit
         * @param hue   hue selected from image by user to differentiate the fruit.
         * @return the user selected image with all instance of the hue converted to black and the rest converted to white
         *//*
        public WritableImage blackWhiteConversion(Image image, Double hue) {
            PixelReader pixelReader = image.getPixelReader();
            WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
            PixelWriter pixelWriter = wImage.getPixelWriter();
            Color blackColor = new Color(0, 0, 0, 1);
            Color whiteColor = new Color(1, 1, 1, 1);
            for (int c = 0; c < image.getWidth(); c++)
                for (int r = 0; r < image.getHeight(); r++) {
                    Color color = pixelReader.getColor(c, r);
                    if ((color.getHue() > hue + 20) || (color.getHue() < hue - 20)) {
                        pixelWriter.setColor(c, r, whiteColor);
                    } else {
                        pixelWriter.setColor(c, r, blackColor);
                    }
                }
            return wImage;
        }
*/


        /*
        public int[] enterPixelsIntoArray(Image image) {
            int[] intArray;
            intArray = new int[(int) image.getWidth() * (int) image.getHeight()];
            Color blackColor = new Color(0, 0, 0, 1);
            PixelReader pixelReader = image.getPixelReader();
            for (int c = 0; c < image.getWidth(); c++) {
                for (int r = 0; r < image.getHeight(); r++) {
                    int arrayPos = ((r * (int) image.getWidth()) + c);
                    Color color = pixelReader.getColor(c, r);
                    if (color.equals(blackColor)) {
                        intArray[arrayPos] = arrayPos;
                    } else {
                        intArray[arrayPos] = -1;
                    }
                }
            }
            return intArray;
        }
        public int[] enterPixelsIntoArray1(){
            int[] intArray;
            intArray = new int[(int)blackWhite.getImage().getWidth()*(int)blackWhite.getImage().getWidth()];
            Color whiteColor= new Color(1,1,1,1);
            PixelReader pixelReader = blackWhite.getImage().getPixelReader();
            for(int i=0;i<intArray.length;i++){
                int pixelX = i%(int)blackWhite.getImage().getWidth();
                int pixelY = i/(int)blackWhite.getImage().getWidth();
                Color color =pixelReader.getColor(pixelX,pixelY);

            }
            return null;
        }

        public int find(int[] imageArray, int data) {
            if (imageArray[data] == data) return data;
            else return find(imageArray, imageArray[data]);
        }


        //unions the disjoint sets into bigger ones
        public void union(int[] imageArray, int a, int b) {
            imageArray[find(imageArray, b)] = find(imageArray, a); //makes the root of b made to reference a
        }

        /**
         * Reads through the image and unions down and right for every pixel not set to -1 as these are not to be considered.
         *
         * @param imageArray array of the pixels
         * @param image      the original image
         * @return the array of pixels as unionised
         */
        /*
        public int[] unionRightAndDown1(int[] imageArray, Image image) {
            for (int i = 0; i < imageArray.length; i++) {
                int right = i + 1;
                int down = i + (int) blackWhite.getImage().getWidth();
                if (imageArray[i] != -1) {
                    if (right < imageArray.length && imageArray[right] != -1) {
                        union(imageArray, i, right);
                    }
                    if (down < imageArray.length && imageArray[down] != 1) {
                        union(imageArray, i, down);
                    }
                }
            }
            return imageArray;
        }

        //Alternative union right and down
        public int[] unionRightAndDown(int[] imageArray, Image image) {
            for (int c = 0; c < image.getWidth(); c++) {
                for (int r = 0; r < image.getHeight(); r++) {
                    int arrayPos = ((r * (int) image.getWidth()) + c);
                    int down = arrayPos + (int) image.getWidth();
                    int right = arrayPos + 1;
                    if (imageArray[arrayPos] != -1) {
                        if ((down < imageArray.length) && (imageArray[down] != -1)) {
                            union(imageArray, arrayPos, down);
                        }
                        if ((right < imageArray.length) && (imageArray[right] != -1)) {
                            union(imageArray, arrayPos, right);
                        }
                    }
                }
            }
            return imageArray;
        }


        /*public WritableImage cornerBoxes(int[] imageArray,WritableImage wImage) {
            //Cycle through the array picking out roots
        for(int c=0;c<wImage.getWidth();c++){
            for(int r=0;r<wImage.getHeight();r++){
                int smallX=0;
                int smallY=0;
                int bigX=0;
                int bigY=0;
                int arrayPos=(r*(int)wImage.getWidth())+c;
                if(imageArray[arrayPos]!=-1) {
                    int currentRoot = find(imageArray, arrayPos);
                    //cycle through the array looking for values where the root is and grab the c,r coords of smallest and largest
                    for (int c1 = 0; c1 < wImage.getWidth(); c1++) {
                        for (int r1 = 0; r1 < wImage.getHeight(); r1++)
                            if (imageArray[(r1 * (int) wImage.getWidth()) + c1] == currentRoot) {
                                if (smallX == 0 || smallX > c1) {
                                    smallX = c1;
                                }
                                if (smallY == 0 || smallY > r1) {
                                    smallY = r1;
                                }
                                if (bigX == 0 || bigX < c1) {
                                    bigX = c1;
                                }
                                if (bigY == 0 || bigY < r1) {
                                    bigY = r1;
                                }
                            }
                    }

                    wImage = drawingBoxes(wImage, smallX, smallY, bigX, bigY);
                }
            }
        }
        return wImage;
        }
        */

// Test method
    /*
        public int[] countRoots(int[] array) {
            int rootCount = 0;
            int k = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == i) {
                    rootCount++;
                }
            }
            int[] rootArray = new int[rootCount];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == j) {
                    k++;
                    rootArray[k] = j;
                }

            }
            return rootArray;
        }
*/
        /*
         * Draws boxes across the axis for each root using their corners.
         *
         * @param wImage writable image from unnamed method
         * @param smallX smallest x for each root
         * @param smallY smallest y for each root
         * @param bigX   biggest x for each root
         * @param bigY   biggest y for each rot
         * @return writable image with the box for the particular root
         */
        /*
        public WritableImage drawingBoxes(WritableImage wImage, int smallX, int smallY, int bigX, int bigY) {
            PixelWriter pixelWriter = wImage.getPixelWriter();
            for (int c = 0; c < wImage.getWidth(); c++) {
                for (int r = 0; r < wImage.getHeight(); r++) {
                    //sx,sy to bx,sy
                    if ((r == smallY) && (smallX < c) && (c < bigX)) {
                        pixelWriter.setColor(c, r, Color.BLUE);
                    }
                    //sx,sy to sx,by
                    if ((c == smallX) && (smallY < r) && (r < bigY)) {
                        pixelWriter.setColor(c, r, Color.BLUE);
                    }
                    //sx,by to bx,by
                    if ((r == bigY) && (smallX < c) && (c < bigX)) {
                        pixelWriter.setColor(c, r, Color.BLUE);
                    }

                    //bx,sy to bx,by
                    if ((c == bigX) && (smallY < r) & (r < bigY)) {
                        pixelWriter.setColor(c, r, Color.BLUE);
                    }
                }

            }
            return wImage;
        }


        /////// NEED TO CYCLE THROUGH ROOTS ARRAY AND CHECK IT AGAINST EVERY ELEMENT.
        ///////
        public WritableImage boxCornersRedo(int[] imageArray, WritableImage wImage) {
            //Cycle through the array picking out roots
            for (int c = 0; c < wImage.getWidth(); c++) {
                for (int r = 0; r < wImage.getHeight(); r++) {
                    int smallX = 0;
                    int smallY = 0;
                    int bigX = 0;
                    int bigY = 0;
                    int arrayPos = (r * (int) wImage.getWidth()) + c;
                    if (imageArray[arrayPos] == arrayPos) {
                        int currentRoot = find(imageArray, arrayPos);
                        //cycle through the array looking for values where the root is and grab the c,r coords of smallest and largest
                        for (int c1 = 0; c1 < wImage.getWidth(); c1++) {
                            for (int r1 = 0; r1 < wImage.getHeight(); r1++)
                                if (imageArray[(r1 * (int) wImage.getWidth()) + c1] == currentRoot) {
                                    if (smallX == 0 || smallX > c1) {
                                        smallX = c1;
                                    }
                                    if (smallY == 0 || smallY > r1) {
                                        smallY = r1;
                                    }
                                    if (bigX == 0 || bigX < c1) {
                                        bigX = c1;
                                    }
                                    if (bigY == 0 || bigY < r1) {
                                        bigY = r1;
                                    }
                                }
                        }

                        wImage = drawingBoxes(wImage, smallX, smallY, bigX, bigY);
                    }
                }
            }
            return wImage;
        }

        /*
         * moves the pixel array into a more accessible form in arrayLists in a hashtable.The key of
         * each arrayList being the root.
         */
        /*
        public void moveArrayIntoHashMap() {
            for (int i = 0; i < imageArray.length; i++) {
                if (!hashMap.containsKey(imageArray[i])) { //if the key hasn't occured yet add it to the hashtable
                    ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    hashMap.put(imageArray[i], arrayList);
                }
                hashMap.get(imageArray[i]).add(i); //adds the current pos in the array to the correct arrayList
            }
        }

        /*
         * Removes entries under a certain size in the hashmap
         *
         * @param threshold defined size for fruit
         */
        /*
        public void removeKeysUnderCertainLengthInHashMap(int threshold) {
            hashMap.remove(-1);
            Iterator<Integer> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                Integer key = it.next();
                if (hashMap.get(key).size() < threshold) {
                    it.remove();
                }
            }
        }

        //rects are created need to be added to the image
        public void boxTime() {
            double smallX, smallY, maxX, maxY;
            double imageWidth = boxImage.getFitWidth();
            for (Integer key : hashMap.keySet()) {
                System.out.println("Key " + key);
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

                constructRects(smallX, smallY, maxX, maxY);

            }

        }

        public void constructRects(double smallX, double smallY, double maxX, double maxY) {
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

            ((Pane) boxImage.getParent()).getChildren().add(rect);

        }


    }

}
*/