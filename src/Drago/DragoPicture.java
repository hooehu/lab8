package Drago;

import javax.swing.*;
import java.awt.*;

public class DragoPicture extends JFrame {
    public DragoPicture() {
        super("Window for Drago");
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(200,100,35,35);
        g.setColor(Color.black);
        g.fillArc(183,110,70,70,-45,90 );
        g.fillArc(183,110,70,70,135,90 );
        g.fillArc(198,72,40,140,-120,60);



    }


}