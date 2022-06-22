package Visual.Serb;

import Visual.LanguageChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class SimpleGuiSerb extends JFrame {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    JButton b1, b2,b3;
    JLabel l1,l2,l3;
    JTextField t;
    public SimpleGuiSerb(){
        setTitle("New Window");
        setLayout(new FlowLayout());
        b1 = new JButton("Промени језик");
        b2 = new JButton("Пријавите се");
        b3 = new JButton("Регистрација");
        l1 = new JLabel(String.valueOf(date));
        l2 = new JLabel(String.valueOf(time));
        add(b2);
        add(b3);
        add(b1);
        add(l1);
        add(l2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(new ButtonEventListener());
        setSize(300,200);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            LanguageChanger changer = new LanguageChanger();

        }

    }
}