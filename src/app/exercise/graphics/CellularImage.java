package app.exercise.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;

public class CellularImage {
    ImageIcon img;
    ImageIcon prev;
    int width, height;

    public CellularImage(String picturelocation) throws IOException {
        img = (new Images(picturelocation)).pattern_img;
        width = (int)(Math.pow(2, (int)(Math.log(img.getIconWidth() * 8) / Math.log(2))));
        height = (int)(Math.pow(2, (int)(Math.log(img.getIconHeight() * 8) / Math.log(2))));

        int oldwidth = img.getIconWidth();
        int oldheight = img.getIconHeight();
        BufferedImage old = (BufferedImage) img.getImage();
        BufferedImage newres = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i >= width/2 - oldwidth/2 && i < width/2 + oldwidth/2 && j >= height/2 - oldheight/2 && j < height/2 + oldheight/2)
                    newres.setRGB(i, j, old.getRGB(i - width/2 + oldwidth/2, j - height/2 + oldheight/2));
                else
                    newres.setRGB(i,j, Color.white.getRGB());
            }
        }
        prev = null;
        img = new ImageIcon(newres);
    }

    public CellularImage(ImageIcon prev) {
        this.prev = prev;
    }
}
