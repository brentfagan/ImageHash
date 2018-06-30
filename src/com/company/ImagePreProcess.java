package com.company;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class ImagePreProcess {

    public static Image readImage(String filePath){
        BufferedImage img = null;
        try {
             img = ImageIO.read(new File(filePath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return img;
    }

    public static Image returnGreyScale(Image colorImg, int newWidth, int newHeight){
        ImageFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(colorImg.getSource(), filter);
        Image greyImage = Toolkit.getDefaultToolkit().createImage(producer);
        Image scaleImage;
        scaleImage = greyImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        return scaleImage;
    }
}
