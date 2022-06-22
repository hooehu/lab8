package VisualCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Visual.EnterVisual.t1;

public class VisualName extends JFrame {
     JLabel label;
    JTextField textName;
     JButton button;
  //  DragonChecker_2 dragonChecker = new DragonChecker_2();
    public VisualName() {
        setTitle("Name");
        setLayout(new FlowLayout());
        textName = new JTextField(10);
        button = new JButton(">>");
        label = new JLabel("Введите имя");
        add(label);
        add(textName);
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        button.addActionListener(new Enter());
    }

    public String getVisualName() {
        String name = textName.getText();
        return name;
    }

    private class Enter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        if (textName.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ошибка ввода, повторите попытку",
                    "",JOptionPane.PLAIN_MESSAGE);
        }
        else {
           
            setVisible(false);
        }
        }
    }
}
