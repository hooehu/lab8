package Visual;

import Managers.CommandManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualIfMin extends JFrame {
    CommandManager cm;
    JLabel label;
    JButton button, back_button;
    JTextField textField;

    public VisualIfMin(CommandManager cm) {
        this.cm = cm;
        setTitle("Add_if_min");
        Box box1 = Box.createHorizontalBox();
        label = new JLabel("Введите id:");
        button = new JButton("Добавить дракона");
        back_button = new JButton("<<");
        textField = new JTextField(10);
        box1.setBorder(new EmptyBorder(12, 12, 12, 12));
        box1.add(label);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(textField);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(back_button);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(box1);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        button.addActionListener(new AddButton());
        back_button.addActionListener(new BackButton());
    }

    private class AddButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            long id = Long.valueOf(textField.getText());
            if (cm.if_min(id)==false){
                cm.if_min_error(id);
            }
            else {
                Visual_Add_If_Min visualAdd = new Visual_Add_If_Min(cm,id);
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
