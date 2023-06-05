package app.exercise.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;

public class CellularImage {
    ImageIcon img;
    CellularImage previous;
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
        previous = null;
        img = new ImageIcon(newres);
    }

    public CellularImage(CellularImage previous) {
        width = previous.img.getIconWidth();
        height = previous.img.getIconHeight();

        this.previous = previous;
        BufferedImage newimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage prev = (BufferedImage) previous.img.getImage();
        
        int counter = 0;

        int r, l, t, b;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                r = (x+1) % width;
                l = (x-1+width) % width;
                t = (y-1+height) % height;
                b = (y+1) % height;

                counter = 0;
                if (prev.getRGB(r,y) == -1) counter++;
                if (prev.getRGB(x,t) == -1) counter++;
                if (prev.getRGB(x,b) == -1) counter++;
                if (prev.getRGB(l,y) == -1) counter++;
                if (prev.getRGB(r,t) == -1) counter++;
                if (prev.getRGB(r,b) == -1) counter++;
                if (prev.getRGB(l,t) == -1) counter++;
                if (prev.getRGB(l,b) == -1) counter++;
                
                if (counter % 2 > 0) {
                    newimage.setRGB(x,y,0);
                }

                else {
                    newimage.setRGB(x,y,-1);
                }
            }
        }
        img = new ImageIcon(newimage);
    }
}
