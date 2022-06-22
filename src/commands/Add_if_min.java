package commands;

import Drago.Dragon;
import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class Add_if_min extends AbstractCommand {
    CommandManager cm;
    Dragon dragon;

    public Add_if_min(CommandManager cm, Dragon dragon) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.cm = cm;
        this.dragon = dragon;
    }

    public void execute() throws IOException {
        try {
            cm.add_if_min(dragon);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}