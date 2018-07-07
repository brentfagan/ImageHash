package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ImagePreProcess {

    public static BufferedImage readImage(File filePath){
        BufferedImage img = null;
        try {
             img = ImageIO.read(filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return img;
    }

    public static BufferedImage returnGrayScaled(BufferedImage colorImg, int newWidth, int newHeight){
//      see https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
        for (int i=0; i<newWidth; i++){
            for (int j=0; j<newHeight; j++){
                int rgb = colorImg.getRGB(i,j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0XFF;
                int b = (rgb & 0xFF);

                double grayDouble = 0.2126*r + 0.7152*g + 0.0722*b;
                int grayLevel = (int) grayDouble;
                int gray = (grayLevel << 16) | (grayLevel << 8) | grayLevel;
                colorImg.setRGB(i, j, gray);
            }
        }
        BufferedImage scaleImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        scaleImage.createGraphics().drawImage(colorImg, 0, 0, newWidth, newHeight, null);
        return scaleImage;
    }

    public static int[][] pixelDifference(BufferedImage grayScale){
        int width = grayScale.getWidth();
        int height = grayScale.getHeight();
        int[][] pixelDiff = new int[width][height];
        for (int j=0; j<height; j++){
            for (int i=0; i<width-1; i++) {
                pixelDiff[i][j] = (grayScale.getRGB(i, j) < grayScale.getRGB(i+1, j)) ? 1 : 0;
            }
        }
        return pixelDiff;
    }

    public static long diffHash(BufferedImage grayScale){
        int width = grayScale.getWidth();
        int height = grayScale.getHeight();
        long dHash = 0;
        for (int j=0; j<height; j++){
            for (int i=0; i<width-1; i++) {
                int bit = (grayScale.getRGB(i, j) < grayScale.getRGB(i+1, j)) ? 1 : 0;
                dHash = dHash << 1 | bit;
            }
        }
        return dHash;
    }

    public static long similarity(long dHash1, long dHash2){
        long compare = dHash1 ^ dHash2;
        int hammingWt = Long.bitCount(compare);
        return hammingWt;
    }
}
