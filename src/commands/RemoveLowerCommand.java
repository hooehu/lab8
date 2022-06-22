package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveLowerCommand extends AbstractCommand {
    CommandManager cm;
    long id;
    public RemoveLowerCommand(CommandManager cm,long id) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.cm = cm;
        this.id = id;
    }
    public void execute() throws IOException {
        try {
            cm.remove_lower(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
