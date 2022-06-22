package commands;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Managers.Connector.getDBConnection;

public class ShowUsersCommand extends AbstractCommand{

    public ShowUsersCommand() {
        super("show_users", "Показывает логины и захэшированные пароли всех пользователей");
    }

    public void execute() throws IOException {
        String s="";
        String selectTableSQL = "SELECT LOGIN, PASSWORD FROM USERS";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String login = rs.getString("LOGIN").trim();
                String password = rs.getString("PASSWORD").trim();
                s+="Логин пользователя: "+login+"\nпароль: "+password +"\n";
            }
            JOptionPane.showMessageDialog(null,s,"Показ пользователей",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
