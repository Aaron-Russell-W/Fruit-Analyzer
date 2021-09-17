package Application;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PixelArrayTest {
    PixelArray pixelArray= new PixelArray();

   @BeforeEach
    void setUp(){
       int[] array = new int[8];
       array[0]=0;
       array[1]=0;
       array[2]=0;
       array[3]=-1;
       array[4] =4;
       array[5]=-1;
       array[6]=6;
       array[7]=6;
       pixelArray.setImageArray(array);
   }
   //Prebuilt array representing what the image array would be with some sets connected. Test shows that at image array pos 1
    // with its value of 0 would be placed under the key 0
   @Test
    public void moveIntoHashMapTest(){
       pixelArray.moveArrayIntoHashMap();
       assertEquals(1,pixelArray.getHashMap().get(0).get(1));
   }

   @Test
   public void removeKeysUnderCertainSizeTest(){
      pixelArray.moveArrayIntoHashMap();
      System.out.println(pixelArray.getHashMap().size());
      pixelArray.removeKeysUnderCertainSize(3);
      assertEquals(1,pixelArray.getHashMap().size());
   }
}