package app.exercise.graphics;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Images {
    ImageIcon original_img;
    ImageIcon grey_img;
    ImageIcon pattern_img;

    public Images(String filelocation) throws IOException {
        original_img = new ImageIcon(filelocation);
        int width = original_img.getIconWidth(), height = original_img.getIconHeight();
        
        int middle = 0;
        Color color;
        BufferedImage original = ImageIO.read(new File(filelocation));
        BufferedImage new_original = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);
        BufferedImage grey = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);
        BufferedImage pattern = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                color = new Color(original.getRGB(x, y));
                new_original.setRGB(x*2, y*2, color.getRGB());
                new_original.setRGB(x*2, y*2+1, color.getRGB());
                new_original.setRGB(x*2+1, y*2, color.getRGB());
                new_original.setRGB(x*2+1, y*2+1, color.getRGB());

                middle += color.getRed();
                middle += color.getGreen();
                middle += color.getBlue();
                middle /= 3;

                grey.setRGB(x*2, y*2, (new Color(middle, middle, middle)).getRGB());
                grey.setRGB(x*2+1, y*2, (new Color(middle, middle, middle)).getRGB());
                grey.setRGB(x*2, y*2+1, (new Color(middle, middle, middle)).getRGB());
                grey.setRGB(x*2+1, y*2+1, (new Color(middle, middle, middle)).getRGB());
                
                if (middle < 53) {
                    pattern.setRGB(x*2, y*2, 0x000000);
                    pattern.setRGB(x*2+1, y*2+1, 0x000000);
                    pattern.setRGB(x*2, y*2+1, 0x000000);
                    pattern.setRGB(x*2+1, y*2, 0x000000); 
                                   
                }

                else if (middle < 105) {
                    pattern.setRGB(x*2, y*2, 0x000000);
                    pattern.setRGB(x*2+1, y*2+1, 0xFFFFFF);
                    pattern.setRGB(x*2, y*2+1, 0xFFFFFF);
                    pattern.setRGB(x*2+1, y*2, 0xFFFFFF); 
                }

                else if (middle < 157) {
                    pattern.setRGB(x*2, y*2, 0xFFFFFF);
                    pattern.setRGB(x*2+1, y*2+1, 0x000000);
                    pattern.setRGB(x*2, y*2+1, 0x000000);
                    pattern.setRGB(x*2+1, y*2, 0x000000); 
                    
                }

                else if (middle < 209) {
                    pattern.setRGB(x*2, y*2, 0x000000);
                    pattern.setRGB(x*2+1, y*2+1, 0x000000);
                    pattern.setRGB(x*2, y*2+1, 0xFFFFFF);
                    pattern.setRGB(x*2+1, y*2, 0xFFFFFF); 
                }

                else {
                    pattern.setRGB(x*2, y*2, 0xFFFFFF);
                    pattern.setRGB(x*2+1, y*2+1, 0xFFFFFF);
                    pattern.setRGB(x*2, y*2+1, 0xFFFFFF);
                    pattern.setRGB(x*2+1, y*2, 0xFFFFFF); 
                }
                middle = 0;
            }
        }

        original_img = new ImageIcon(new_original);
        grey_img = new ImageIcon(grey);
        pattern_img = new ImageIcon(pattern);
    }
}
