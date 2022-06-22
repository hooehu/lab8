package Visual;

import Visual.*;
import Visual.Eng.*;
import Visual.Ru.*;
import Visual.Serb.SimpleGuiSerb;
import Visual.Veng.SimpleGuiVeng;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageChanger extends JFrame {
    JRadioButton b1,b2,b3,b4;
    public LanguageChanger(){
        setTitle("New Window");
        setLayout(new FlowLayout());
        b1 = new JRadioButton("Русский");
        b2 = new JRadioButton("Српски");
        b3 = new JRadioButton("Magyar");
        b4 = new JRadioButton("English");
        JButton button = new JButton(">>");
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        group.add(b3);
        group.add(b4);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        b1.setSelected(true);
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener( new ButtonAction());
        setSize(300,200);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    class ButtonAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
        if (b1.isSelected()){
            SimpleGuiRu simpleGuiRu= new SimpleGuiRu();
        }
        else if (b2.isSelected()){
            SimpleGuiSerb simpleGuiSerb = new SimpleGuiSerb();
        }
        else if(b3.isSelected()){
            SimpleGuiVeng simpleGuiVeng = new SimpleGuiVeng();
        }
           else {
            SimpleGuiEng simpleGuiEng = new SimpleGuiEng();
        }
        }
    }
}
