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
    JLabel picture_label;
    CalcThread calc_thread;
    ShowingThread show_thread;

    private final String picturelocation;

    public CellularFrame(String title, String picturelocation) throws IOException {
        super(title);

        this.picturelocation = picturelocation;
        this.image = new CellularImage(picturelocation);

        picture_label = new JLabel(image.img);
        picture_label.setBounds(0, 38, image.img.getIconWidth(), image.img.getIconHeight());
        this.add(picture_label);

        this.setLayout(null);
        this.setSize(image.width, image.height + 110);
        setUpperpanel();
        setPicture();
        setUnderpanel();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        calc_thread = new CalcThread(this);
        show_thread = new ShowingThread(this);
    }

    public void setNewPicture() {
        image = new CellularImage(image);
    }

    public void setPicture() {
        picture_label.setIcon(image.img);
        this.update(getGraphics());
    }

    public void setUnderpanel() {
        JPanel underpanel = new JPanel();
        underpanel.setBounds(0, 38 + image.img.getIconHeight(), image.img.getIconWidth(), 38);
        underpanel.setBackground(new Color(211, 211, 211));
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
        upperpanel.setBounds(0, 0, image.img.getIconWidth(), 38);
        upperpanel.setBackground(new Color(211, 211, 211));

        JLabel rounds = new JLabel("Rounds");
        rounds.setBounds(5, 5, 70, 30);
        upperpanel.setLayout(null);

        JTextField round = new JTextField();
        round.setBounds(75, 5, 150, 30);

        JButton start = new JButton("Start");
        start.setBounds(230, 7, 70, 27);

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int max;
                try {
                    max = Integer.valueOf(round.getText());
                    round.setText("");
                }

                catch (NumberFormatException e) {
                    round.setText("I need a positive Integer, less than 270");
                    return;
                }


                if (max < 0 || max > 269) {
                    round.setText("I need a positive Integer, less than 270");
                    return;
                }

                try {
                    image = new CellularImage(picturelocation);
                } catch (IOException e) {};

                setPicture();
                int j = 0;
                while ( j++ < max) {
                    calc_thread.run();
                    show_thread.run();
                }
            }

        });

        upperpanel.add(start);
        upperpanel.add(round);
        upperpanel.add(rounds);
        this.add(upperpanel);
    }
}
