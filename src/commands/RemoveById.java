package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveById extends AbstractCommand {
    CommandManager cm;
    long id;
    public RemoveById(CommandManager cm,long id) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.cm = cm;
        this.id = id;
    }

    public void execute() throws IOException {
        cm.remove_by_id(id);
    }
}
