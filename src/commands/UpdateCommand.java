package commands;

import Drago.Dragon;
import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCommand extends AbstractCommand {
    CommandManager cm;
    Dragon dragon;
    public UpdateCommand(CommandManager cm,Dragon dragon) {
        super("update_by_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.cm = cm;
        this.dragon = dragon;
    }


    public void execute() throws IOException {
        try {
            cm.update(dragon);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}