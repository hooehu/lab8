package Visual;

import Managers.CommandManager;
import commands.Remove_any_by_description;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class D_Remover extends JFrame {

    CommandManager cm;
    JTextField field;

    public D_Remover(CommandManager cm) {
        this.cm = cm;
        setTitle("Remove_by_description");
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel("Введите описание дракона:");
        field = new JTextField(15);
        JButton button = new JButton("Удалить");
        box.add(label);
        box.add(Box.createHorizontalStrut(6));
        box.add(field);
        Box box1 = Box.createHorizontalBox();
        JButton button1 = new JButton("<<");
        box1.add(button1);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(button);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box1);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        button.addActionListener(new RemoveButton());
        button1.addActionListener(new BackButton());
    }

    private class RemoveButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Remove_any_by_description r = new Remove_any_by_description(cm,field.getText());
            try {
                r.execute();
            } catch (IOException e) {
                e.printStackTrace();
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
