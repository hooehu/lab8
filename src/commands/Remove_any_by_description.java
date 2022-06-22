package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class Remove_any_by_description extends AbstractCommand {
    CommandManager cm;
    String description;
    public Remove_any_by_description(CommandManager cm, String description) {
        super("remove_any_by_description", "удалить из коллекции один элемент, значение поля description которого эквивалентно заданному");
        this.cm = cm;
        this.description = description;
    }

    public void execute() throws IOException {
        try {
            cm.remove_by_d(description);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}