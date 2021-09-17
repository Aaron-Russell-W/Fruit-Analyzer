package Application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static javafx.scene.paint.Color.color;

public class PixelArray {
    private int[] imageArray;
    private HashMap<Integer, Integer> sizeHashMap = new HashMap<Integer, Integer>();
    private HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
    UnionFind unionFind = new UnionFind();

    /**
     * Reads through image pixel by pixel and gives each pixel a corresponding location in the array. All black pixels(fruit)
     * keeps its position in the array as its value and all white is set to -1 to be ignored;
     *
     * @param image image selected by user
     */
    public void enterPixelsIntoArray(Image image) {
        int[] tempArray = new int[(int) image.getWidth() * (int) image.getHeight()];
        PixelReader pixelReader = image.getPixelReader();
        Color white = new Color(1, 1, 1, 1);
        for (int i = 0; i < tempArray.length; i++) {
            int x = i % (int) image.getWidth();
            int y = i / (int) image.getWidth();
            Color color = pixelReader.getColor(x, y);
            if (color.equals(white)) {
                tempArray[i] = i;
            } else {
                tempArray[i] = -1;
            }
        }
        setImageArray(tempArray);

    }

    /**
     * Moves array values into a hashmap where the key for the value is its root.
     */
    public void moveArrayIntoHashMap() {
        for (int i = 0; i < imageArray.length; i++) {
            if (imageArray[i] != -1) {
                int newInt = unionFind.find(imageArray, i);

                if (!hashMap.containsKey(imageArray[newInt])) { //if the key hasn't occurred yet add it to the hashtable
                    ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    hashMap.put(imageArray[newInt], arrayList);
                }
                hashMap.get(imageArray[newInt]).add(i); //adds the current pos in the array to the correct arrayList
            }
        }

    }

    /**
     * Removes hashMap key sets under a certain amount
     * @param threshold under this amount are removed from hashMap
     */
    public void removeKeysUnderCertainSize(int threshold) {
        hashMap.remove(-1);
        hashMap.keySet().removeIf(key -> hashMap.get(key).size() < threshold);
    }

    public HashMap<Integer, ArrayList<Integer>> getHashMap() {
        return hashMap;
    }

    public int[] getImageArray() {
        return imageArray;
    }

    public void setHashMap(HashMap<Integer, ArrayList<Integer>> hashMap) {
        this.hashMap = hashMap;
    }

    public void setImageArray(int[] imageArray) {
        this.imageArray = imageArray;
    }

    /**
     * reports numbers of pixels in each keyset and how many key sets are available
     */
    public void reportNumberOfPixelsInEachSet() {
        for (Integer key : hashMap.keySet()) {
            int count = hashMap.get(key).size();
            System.out.println("For set with root " + key + ": Contains " + count + " pixels");
        }
        System.out.println("Number of fruit in image =" + hashMap.size());
    }
}
