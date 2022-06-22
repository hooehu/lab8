package Visual;

import Managers.CommandManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualIfMax extends JFrame {
    CommandManager cm;
    JLabel label;
    JButton button, button1;
    JTextField textField;

    public VisualIfMax(CommandManager cm) {
        this.cm = cm;
        setTitle("Add_if_max");
        Box box1 = Box.createHorizontalBox();
        label = new JLabel("Введите id:");
        button = new JButton("Добавить дракона");
        button1 = new JButton("<<");
        textField = new JTextField(10);
        box1.setBorder(new EmptyBorder(12, 12, 12, 12));
        box1.add(label);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(textField);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(button1);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(box1);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        button.addActionListener(new ExecuteButton());
        button1.addActionListener(new BackButton());

    }

    private class ExecuteButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            long id = Long.valueOf(textField.getText());
            if (cm.if_max(id) == false) {
            cm.if_max_error();
            } else {
                Visual_Add_If add_if = new Visual_Add_If(cm, id);
                setVisible(false);
            }
        }
    }

    private class BackButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            MainMenu mainMenu = new MainMenu(cm);
            setVisible(false);
        }
    }
}
