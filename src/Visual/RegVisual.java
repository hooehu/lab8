package Visual;

import Users.Hash;
import Visual.Eng.SimpleGuiEng;
import Visual.Ru.SimpleGuiRu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Managers.Connector.getDBConnection;

public class RegVisual  extends JFrame {
    JTextField t1, t2;
    JLabel l1, l2;
    JButton button;

    public RegVisual() {
        setTitle("Enter");
        setLayout(new FlowLayout());
        l1 = new JLabel("Введите логин");
        t1 = new JTextField(10);
        l2 = new JLabel("Введите пароль");
        t2 = new JTextField(10);
        button = new JButton("Registration");
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        button.addActionListener(new ButtonEventListener());
    }

    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int a = 1;
            while (a == 1) {
                Connection dbConnection = null;
                Statement statement = null;
                Hash hash= new Hash();

                String insertTableSQL = "INSERT INTO USERS"
                        + "(LOGIN, PASSWORD) " + "VALUES"
                        + "('" + t1.getText() + "', '" + hash.hashPassword(t2.getText()) + "')";
                try {
                    dbConnection = getDBConnection();
                    statement = dbConnection.createStatement();
                    // выполнить SQL запрос
                    statement.executeUpdate(insertTableSQL);
                    System.out.println("Table \"users\" is updated!");
                    a = 0;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Повторите попытку ");
                    a = 1;
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (dbConnection != null) {
                        try {
                            dbConnection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            SimpleGuiRu simpleGuiRu = new SimpleGuiRu();
            setVisible(false);
        }
    }
}
