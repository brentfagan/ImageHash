package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ImagePreProcess {

    public static BufferedImage readImage(String filePath){
        BufferedImage img = null;
        try {
             img = ImageIO.read(new File(filePath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return img;
    }

    public static BufferedImage returnGrayScaled(BufferedImage colorImg, int newWidth, int newHeight){
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

    public static void printPixels(BufferedImage greyScale){
        int width = greyScale.getWidth();
        int height = greyScale.getHeight();
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++) System.out.println(greyScale.getRGB(i,j));
        }
    }
}
