package Visual;

import Managers.CommandManager;
import Users.Enter;
import Users.Hash;
import Visual.Eng.SimpleGuiEng;
import Visual.Ru.SimpleGuiRu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Managers.Connector.getDBConnection;
public class EnterVisual extends JFrame {
    public static JTextField t1, t2;
    JLabel l1, l2;
    JButton button, button1;
    CommandManager cm = new CommandManager();

    public EnterVisual() {
        setTitle("Enter");
        //setLayout(new FlowLayout());
        Box box1 = Box.createHorizontalBox();
        l1 = new JLabel("Логин:");
        t1 = new JTextField(15);
        box1.add(l1);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(t1);
        //l1 = new JLabel("Введите логин");
        //t1 = new JTextField(10);
        Box box2 = Box.createHorizontalBox();
        l2 = new JLabel("Пароль:");
        t2 = new JTextField(15);
        box2.add(l2);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(t2);
        Box box3 = Box.createHorizontalBox();
        button = new JButton("Войти");
        button1 = new JButton("<<");
        box3.add(Box.createHorizontalGlue());
        box3.add(button1);
        box3.add(Box.createHorizontalStrut(37));
        box3.add(button);

        l1.setPreferredSize(l2.getPreferredSize());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        button.addActionListener(new ButtonEventListener());
        button1.addActionListener(new BackButton());
    }

    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            boolean isUserExists = false;
            int a = 0;
            while (isUserExists == false) {
                Connection dbConnection = null;
                Statement statement = null;

                Hash hash = new Hash();
                String selectTableSQL = "SELECT from USERS WHERE LOGIN = '" + t1.getText() + "' AND PASSWORD = '" + hash.hashPassword(t2.getText()) + "'";
                try {
                    dbConnection = getDBConnection();
                    statement = dbConnection.createStatement();


                    ResultSet rs = statement.executeQuery(selectTableSQL);
                    if (rs.next()) {
                        isUserExists = true;
                    }
                    if (isUserExists) {
                        JOptionPane.showMessageDialog(null, "Выполнен вход под логином " + t1.getText(), "Message", JOptionPane.INFORMATION_MESSAGE);
                        MainMenu mainMenu = new MainMenu(cm);
                        setVisible(false);
                        cm.read();
                    } else {
                        JOptionPane.showMessageDialog(null, "Неправильный логин или пароль", "Message", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

    private class BackButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        SimpleGuiRu simpleGuiRu = new SimpleGuiRu();
        }
    }
}
