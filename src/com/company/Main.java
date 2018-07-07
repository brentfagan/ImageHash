package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final long startTimeAll = System.currentTimeMillis();
        double cnt = 0.0;
        List<Long> avgTime = new ArrayList<>();
        List<Long> hashList = new ArrayList<>();
        File[] directories = new File("/Users/brentfagan/Desktop/Projects/html_parse/image_out").
                                        listFiles(File::isDirectory);
        for (int i=0; i<directories.length; i++){
            File[] allPages = directories[i].listFiles();
            for (int j=0; j<allPages.length; j++){
                cnt += 1.0;
//                System.out.println(allPages[j]);
                final long startTime = System.currentTimeMillis();
                BufferedImage img = ImagePreProcess.readImage(allPages[j]);
                BufferedImage gs = ImagePreProcess.returnGrayScaled(img, 9, 8);
                long dHash = ImagePreProcess.diffHash(gs);
                final long endTime = System.currentTimeMillis();
                System.out.println("Execution time: " + (endTime - startTime) );
                avgTime.add(endTime-startTime);
//                hashList.add(dHash);
            }
//            System.out.println(directories[i]);
        }
        long sum = avgTime.stream().mapToLong(a -> a).sum();
        System.out.println("Average execution time: " + sum/cnt );
        final long endTimeAll = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTimeAll - startTimeAll) );
//        System.out.println(hashList.size());
//        BufferedImage img1 = ImagePreProcess.readImage("/home/brent/TensorFlow Examples/cat1.jpg");
//        BufferedImage gs1 = ImagePreProcess.returnGrayScaled(img1, 9, 8);
//        BufferedImage img2 = ImagePreProcess.readImage("/home/brent/TensorFlow Examples/cat1.jpg");
//        BufferedImage gs2 = ImagePreProcess.returnGrayScaled(img2, 9, 8);
//        long dHash1 = ImagePreProcess.diffHash(gs1);
//        long dHash2 = ImagePreProcess.diffHash(gs2);
//        long result = ImagePreProcess.similarity(dHash1, dHash2);
//        System.out.println(result);
//        int[][] arr = ImagePreProcess.pixelDifference(gs1);
//        System.out.println(Arrays.deepToString(arr));
    }
}
