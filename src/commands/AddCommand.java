package commands;

import Drago.Dragon;
import Managers.CommandManager;
import Users.Enter;

import java.io.IOException;
import java.sql.SQLException;

public class AddCommand extends AbstractCommand {
    CommandManager cm;
    Dragon dragon;
    Enter enter = new Enter();
    public AddCommand(CommandManager cm, Dragon dragon) {
        super("add", "добавить элемент в коллекцию");
        this.cm = cm;
        this.dragon = dragon;
    }

    public void execute()  {

        try {
            cm.add(dragon);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

