package VisualCheck;

import Drago.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualCoordinates extends JFrame {
    JLabel l1, l2;
    JTextField XTextfield, YTextfield;
    JButton button;
    int x;
    long y;
    Coordinates c;
    String message = "";

    public VisualCoordinates() {
        setTitle("Coordinates");
        setLayout(new FlowLayout());
        l1 = new JLabel("Введите x:");
        l2 = new JLabel("Введите y");
        XTextfield = new JTextField(10);
        YTextfield = new JTextField(10);
        button = new JButton(">>");
        add(l1);
        add(XTextfield);
        add(l2);
        add(YTextfield);
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        button.addActionListener(new Enter());
    }

    public int getx() {
        return x;
    }

    public long gety() {
        return y;
    }

    private class Enter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int a = 0;
            if (Coordinates.min_x > Integer.valueOf(XTextfield.getText())) {
                message += "x должен быть больше, чем " + Coordinates.min_x;
                a += 1;
            } else {
                x = Integer.valueOf(XTextfield.getText());
            }
            if (Coordinates.min_y > Long.valueOf(YTextfield.getText())) {
                message += "y должен быть больше, чем " + Coordinates.min_y;
                a += 1;
            } else {
                y = Long.valueOf(YTextfield.getText());
            }
            if (a > 0) {
                JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
            } else {
                c = new Coordinates(x, y);
                setVisible(false);
            }
        }


        }
    public Coordinates getCoord() {
        return c;
    }
}

