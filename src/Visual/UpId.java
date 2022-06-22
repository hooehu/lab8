package Visual;

import Managers.CommandManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Visual.EnterVisual.t1;

public class UpId extends JFrame {
    CommandManager cm;
    JTextField field;

    public UpId(CommandManager cm) {
        this.cm = cm;
        setTitle("Update");
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel("Введите id дракона:");
        field = new JTextField(15);
        JButton button = new JButton("Обновить");
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
        button.addActionListener(new UpdateButton());
        button1.addActionListener(new BackButton());
    }

    private class UpdateButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        long id = Long.valueOf(field.getText());
        if(cm.CheckId(id)==true) {
            Updater updater = new Updater(cm,id);
            setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Дракона с таким "+id+" от пользователя "+t1.getText()+" не существует.", "Message",JOptionPane.INFORMATION_MESSAGE);
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

