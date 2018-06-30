package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ImagePreProcess {
    public static void countBits(String filePath){
        int newWidth = 8;
        int newHeight = 9;
        BufferedImage colorImg = null;
        try {
            colorImg = ImageIO.read(new File(filePath));
            ImageFilter filter = new GrayFilter(true, 50);
            ImageProducer producer = new FilteredImageSource(colorImg.getSource(), filter);
            Image greyImage = Toolkit.getDefaultToolkit().createImage(producer);
            Image scaleImage = greyImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            int height;
            height = colorImg.getHeight();
            int width;
            width = colorImg.getWidth();
            System.out.println("Height : " + height);
            System.out.println("Width : " + width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
