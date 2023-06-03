package app.exercise.graphics;

import java.io.IOException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CellularFrame extends JFrame {
    ImageIcon img;
    public CellularFrame(String title, String picturelocation) throws IOException {
        super(title);
        img = (new Images(picturelocation)).pattern_img;
        int width = (int)(Math.pow(2, (int)(Math.log(img.getIconWidth() * 8) / Math.log(2))));
        int height = (int)(Math.pow(2, (int)(Math.log(img.getIconHeight() * 8) / Math.log(2))));

        this.setSize(width, height);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPicture();
        setExitButton();
        setUpperPanel();
        this.update(getGraphics());
    }

    public void setPicture() {
        JPanel middlepannel = new JPanel();
        middlepannel.setBounds(this.getWidth() / 2 - img.getIconWidth()/2 , this.getHeight() / 2 - img.getIconHeight()/2, img.getIconWidth(), img.getIconHeight());

        JLabel piclab = new JLabel(img);
        middlepannel.add(piclab);
        this.add(middlepannel);
    }

    public void setExitButton() {
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        JPanel exitpan = new JPanel();
        exitpan.setBounds(this.getWidth() - 65, this.getHeight() - 75, 65, 75);
        exitpan.add(exit);
        this.add(exitpan);
    }

    public void setUpperPanel() {
        JPanel upperpan = new JPanel();
        upperpan.setBackground(new Color(211, 211, 211));
        upperpan.setBounds(0, 0, this.getWidth()+10, 45);


        upperpan.setLayout(null);

        JLabel upperlabel = new JLabel("Rounds");
        JTextField field = new JTextField();
        field.setBounds(60,5,40,25);
        upperlabel.setBounds(0,5,150,30);
        upperlabel.setLayout(null);
        upperlabel.add(field);
    
        upperpan.add(upperlabel);
        

        JButton start = new JButton("Start");

        start.setBounds(110, 7, 70, 30);
        upperpan.add(start);
        this.add(upperpan);
    }
}
