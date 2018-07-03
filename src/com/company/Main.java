package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        BufferedImage img1 = ImagePreProcess.readImage("/home/brent/TensorFlow Examples/cat1.jpg");
        BufferedImage gs1 = ImagePreProcess.returnGrayScaled(img1, 9, 8);
        BufferedImage img2 = ImagePreProcess.readImage("/home/brent/TensorFlow Examples/cat1.jpg");
        BufferedImage gs2 = ImagePreProcess.returnGrayScaled(img2, 9, 8);
        long dHash1 = ImagePreProcess.diffHash(gs1);
        long dHash2 = ImagePreProcess.diffHash(gs2);
        long result = ImagePreProcess.similarity(dHash1, dHash2);
        System.out.println(result);
//        int[][] arr = ImagePreProcess.pixelDifference(gs);
//        System.out.println(Arrays.deepToString(arr));
    }
}
