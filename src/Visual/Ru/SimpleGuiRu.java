package Visual.Ru;

import Managers.CommandManager;
import Managers.Commander;
import Visual.EnterVisual;
import Visual.LanguageChanger;
import Visual.RegVisual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class SimpleGuiRu extends JFrame {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    JButton b1, b2, b3;
    JComboBox j;
    JLabel l1, l2;
    public SimpleGuiRu() {
        String[] s = {
               "English",
                "Русский",
                "Magyar",
                "Српски"
        };
        setTitle("New Window");
        Box box1 = Box.createHorizontalBox();
        b1 = new JButton("Войти");
        b2 = new JButton("Регистрация");
        box1.add(b1);
        //box1.add(Box.createHorizontalStrut(6));
        Box box2 = Box.createHorizontalBox();
        box2.add(b2);

        j = new JComboBox(s);
        l1 = new JLabel(String.valueOf(date));
        Box box3 = Box.createHorizontalBox();
        box3.add(j);
        //box2.add(Box.createHorizontalStrut(6));
        Box box4 = Box.createHorizontalBox();
        box4.add(l1);
        b1.setPreferredSize(b2.getPreferredSize());
        j.setPreferredSize(b1.getPreferredSize());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(40,40,40,40));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box4);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        b1.addActionListener(new EnterEventListener());
        b2.addActionListener(new RegEventListener());
    }

    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            LanguageChanger changer = new LanguageChanger();
            setVisible(false);
        }
    }
    private class EnterEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            EnterVisual enterVisual = new EnterVisual();
            setVisible(false);
        }
    }
    private class RegEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            RegVisual regVisual = new RegVisual();
            setVisible(false);
        }
    }
}


