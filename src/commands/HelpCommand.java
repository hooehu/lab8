package commands;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
public class HelpCommand extends AbstractCommand {
    private HashMap<String, Command> commands;
    public HelpCommand(HashMap<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }
    public void execute() {
        String s = "";
        for (Map.Entry<String, Command> command : commands.entrySet()) {
             //System.out.print(command.getValue().getName() + ": ");
            //System.out.println(command.getValue().getDescription());
            s += command.getValue().getName() + ": " + command.getValue().getDescription() + "\n";
            //JOptionPane.showMessageDialog(null, command.getValue().getName() + ": " + command.getValue().getDescription(), "HelpMessage", JOptionPane.INFORMATION_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, s, "HelpMessage", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("ooo");
    }
}

