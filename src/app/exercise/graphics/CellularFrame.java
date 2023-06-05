package app.exercise.graphics;

import java.io.IOException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CellularFrame extends JFrame {
    CellularImage image;

    public CellularFrame(String title, String picturelocation) throws IOException {
        super(title);

        this.image = new CellularImage(picturelocation);
        int i = 0;
        while (i++ < 192)
            setNewPicture();

        this.setLayout(null);
        this.setSize(image.width, image.height+110);
        setUpperpanel();
        setPicture();
        setUnderpanel();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void setNewPicture() {
        image = new CellularImage(image);
    }

    public void setPicture() {
        JLabel label = new JLabel(image.img);
        label.setBounds(0,38,image.img.getIconWidth(), image.img.getIconHeight());
        this.add(label);
    }

    public void setUnderpanel() {
        JPanel underpanel = new JPanel();
        underpanel.setBounds(0, 38 + image.img.getIconHeight(), image.img.getIconWidth(), 38);
        underpanel.setBackground(new Color(211,211,211));
        underpanel.setLayout(null);

        JButton exit = new JButton("Exit");
        exit.setBounds(image.img.getIconWidth() - 75, 5, 70, 27);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        underpanel.add(exit);
        this.add(underpanel);
    }

    public void setUpperpanel() {
        JPanel upperpanel = new JPanel();
        upperpanel.setBounds(0,0,image.img.getIconWidth(), 38);
        upperpanel.setBackground(new Color(211, 211, 211));

        JLabel rounds = new JLabel("Rounds");
        rounds.setBounds(5,5,70,30);
        upperpanel.setLayout(null);
        
        JTextField round = new JTextField();
        round.setBounds(75, 5, 70, 30);

        JButton start = new JButton("Start");
        start.setBounds(150, 7, 70, 27);

        upperpanel.add(start);
        upperpanel.add(round);
        upperpanel.add(rounds);
        this.add(upperpanel);
    }
}
