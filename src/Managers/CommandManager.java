package Managers;

import Drago.*;
import Drago.Character;
import Users.Enter;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;


import static Managers.Connector.getDBConnection;
import static Visual.EnterVisual.t1;

public class CommandManager {
    DragonChecker dragonChecker = new DragonChecker();
   public static TreeSet<Dragon> ts = new TreeSet<>();
    Connection dbConnection = null;
    Statement statement = null;
    private Statement request;

    public TreeSet<Dragon> read() {

        String selectTableSQL = "SELECT ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD, LOGIN from DRAGON";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String id = rs.getString("ID");
                Long id1 = Long.valueOf(id);
                String name = rs.getString("NAME").trim();
                String name1 = String.valueOf(name).trim();
                String x = rs.getString("X");
                String y = rs.getString("Y");
                int x1 = Integer.valueOf(x);
                long y1 = Long.valueOf(y);
                String date = rs.getString("DATE");
                LocalDate creationDate = LocalDate.parse(date);
                Coordinates coordinates = new Coordinates(x1, y1);
                String description = rs.getString("DESCRIPTION");
                String description1 = String.valueOf(description).trim();
                String age = rs.getString("AGE");
                Integer age1 = Integer.valueOf(age);
                String weight = rs.getString("WEIGHT");
                Integer weight1 = Integer.valueOf(weight);
                String dragoncharacter = rs.getString("DRAGONCHARACTER").trim();
                Character character = new Character();
                String dragonhead = rs.getString("DRAGONHEAD");
                Double eyescount = Double.valueOf(dragonhead);
                DragonHead dragonHead1 = new DragonHead(eyescount);
                String login = rs.getString("LOGIN").trim();
                Dragon dragon = new Dragon(id1, name, coordinates, creationDate, description1, age1, weight1, character.Character(dragoncharacter), dragonHead1, login);
                ts.add(dragon);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ts;
    }

    public void save() throws SQLException {
        try {
            Connector connector = new Connector();
            connector.Delete();
            for (Dragon dragon : ts) {
                String insert = "INSERT INTO DRAGON (LOGIN, ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)"
                        + "VALUES ('" + dragon.getLogin() + "', " + dragon.getId() + ", '" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "', '" + dragon.getDescription() + "', " + dragon.getAge() + ", " + dragon.getWeight() + ", '" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";
                try {
                    dbConnection = getDBConnection();
                    statement = dbConnection.createStatement();
                    statement.executeUpdate(insert);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Info Message", JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    if (statement != null) {
                        statement.close();
                    }
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Changes are saved successfully.", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not load the changes. Please try later.", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ioException) {
            JOptionPane.showMessageDialog(null, "Something bad with saving. Try again.", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void info() {
        String s = "";
        if (ts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка, Сначала добавьте элементы в коллекцию", "HelpMessage", JOptionPane.INFORMATION_MESSAGE);
        } else {
            s += "Коллекция типа " + ts.getClass().getName() + "\n";
            s += "Количество элементов в коллекции " + ts.size() + "\n";
            s += "Дата создания коллекции: " + ts.first().getCreationDate() + "\n";
            JOptionPane.showMessageDialog(null, s, "InfoMessage", JOptionPane.INFORMATION_MESSAGE);

        }
    }


    public void exit() {
        System.out.println("завершение работы");
        System.exit(0);
    }


    public TreeSet<Dragon> add(Dragon dragon) throws SQLException {

        DragonChecker dragonChecker = new DragonChecker();
        dragon.setId(dragonChecker.ID(ts));
        String insertTableSQL = "INSERT INTO DRAGON"
                + "(LOGIN, ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                + "('" + t1.getText() + "', " + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "', '" + dragon.getDescription() + "', " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);
            String s = "Table \"dragon\" is updated! \n";
            ts.add(dragon);
            s += "Дракон успешно добавлен в коллекцию";
            JOptionPane.showMessageDialog(null, s, "Error", JOptionPane.WARNING_MESSAGE);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return ts;
    }

    public void show() {
        final String[] s = {""};
        ts.stream().forEach(dragon -> s[0] +=dragon +"\n"); // Stream API с лямбдой
        JOptionPane.showMessageDialog(null, s, "Show", JOptionPane.INFORMATION_MESSAGE);

        if (ts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Коллекция пуста", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public TreeSet<Dragon> clear() throws SQLException {
        ts.removeIf(dragon -> dragon.getLogin().equals(t1.getText()));
        long l = 1;
        for (Dragon dragon : ts) {
            dragon.setId(l);
            l += 1;
        }
        String message = "Из коллекции удалены все элементы, создателем которых является " + t1.getText();
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
        return ts;
    }

    public boolean if_max(long id) {
        if (id > ts.last().getId()) {
            return true;
        } else {
            return false;
        }
    }

    public void if_max_error() {
        JOptionPane.showMessageDialog(null, "Ошибка, id дракона должен быть больше " + ts.last().getId(), "Message", JOptionPane.WARNING_MESSAGE);
    }

    public boolean if_min(long id) {
        if (id < ts.first().getId() & id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void if_min_error(long id) {
        if (id <= 0) {
            JOptionPane.showMessageDialog(null, "Значение id не может быть меньше 0", "Message", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ошибка, id дракона должен быть меньше " + ts.first().getId(), "Message", JOptionPane.WARNING_MESSAGE);
        }
    }

    public TreeSet<Dragon> add_if_max(Dragon dragon) throws SQLException {

        String insertTableSQL = "INSERT INTO DRAGON"
                + "(LOGIN, ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                + "('" + dragon.getLogin() + "', " + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "', '" + dragon.getDescription() + "', " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);
            JOptionPane.showMessageDialog(null, "Table \"dragon\" is updated! \nДракон успешно добавлен в коллекцию", "Message", JOptionPane.INFORMATION_MESSAGE);
            ts.add(dragon);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return ts;
    }

    public TreeSet<Dragon> add_if_min(Dragon dragon) throws SQLException {
        try {
            String insertTableSQL = "INSERT INTO DRAGON"
                    + "(LOGIN, ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                    + "('" + dragon.getLogin() + "', " + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "', '" + dragon.getDescription() + "', " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";

            try {
                dbConnection = getDBConnection();
                statement = dbConnection.createStatement();
                statement.executeUpdate(insertTableSQL);
                JOptionPane.showMessageDialog(null, "Table \"dragon\" is updated! \nДракон успешно добавлен в коллекцию", "Message", JOptionPane.INFORMATION_MESSAGE);
                ts.add(dragon);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
        } catch (InputMismatchException exception) {
            System.out.println("Значение id должно быть представлено числом");
        } catch (NullPointerException exception) {
            System.out.println("Поле не может быть null");
        } catch (NoSuchElementException exception) {
            System.out.println("Коллекция пуста, сначала добавьте элементы");
        }
        return ts;
    }


    public TreeSet<Dragon> remove_by_id(long id) {
        int a = 0;

        if (ts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Коллекция пуста, сначала добавьте драконов!", "Message", JOptionPane.INFORMATION_MESSAGE);

        } else {
            try {
                for (Dragon dragon : ts) {
                    if (dragon.getId() == id & dragon.getLogin().equals(t1.getText())) {
                        a = 1;
                    }
                }
                if (a == 1) {
                    ts.removeIf(dragon -> dragon.getId() == id);
                    JOptionPane.showMessageDialog(null, "Дракон с id " + id + " удален", "Message", JOptionPane.INFORMATION_MESSAGE);
                    long l = 1;
                    for (Dragon dragon : ts) {
                        dragon.setId(l);
                        l += 1;
                    }
                }

                if (a == 0) {
                    JOptionPane.showMessageDialog(null, "Дракона с таким id, пренадлежащего пользователю " + t1.getText() + " не существует", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (InputMismatchException exception) {
                System.out.println("Значение id должно быть представлено числом");
            } catch (NullPointerException exception) {
                System.out.println("Поле не может быть null");
            }
        }

        return ts;
    }


    public TreeSet<Dragon> remove_by_d(String description) throws SQLException {
        int a = 0;
        if (ts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Коллекция пуста, сначала добавьте драконов!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Dragon dragon : ts) {
                if (dragon.getDescription().equals(description) & dragon.getLogin().equals(t1.getText())) {
                    a = 1;
                }
            }
            if (a == 1) {
                ts.removeIf(dragon -> dragon.getDescription().equals(description) & dragon.getLogin().equals(t1.getText()));
                JOptionPane.showMessageDialog(null, "Все драконы от " + t1.getText() + " с описанием " + description + " удалены", "Message", JOptionPane.INFORMATION_MESSAGE);
                long l = 1;
                for (Dragon dragon : ts) {
                    dragon.setId(l);
                    l += 1;
                }
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Дракона с таким описанием и созданным пользователем " + t1.getText() + " не существует, поробуйте снова", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }


        return ts;
    }

    public boolean CheckId(long id) {
        int a = 0;
        for (Dragon dragon : ts) {
            if (id == dragon.getId() & dragon.getLogin().equals(t1.getText())) {
                a = 1;
                break;
            } else {
                a = 0;
            }
        }
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }


    public TreeSet<Dragon> update(Dragon d) throws SQLException {
        int a = 0;
            if (ts.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Коллекция пуста, сначала добавьте драконов", "Message", JOptionPane.INFORMATION_MESSAGE);
                a = 1;
            } else {
                    for (Dragon dragon : ts) {
                        if (dragon.getId() == d.getId()) {
                            if (dragon.getLogin().equals(t1.getText())) {
                                dragon.setName(d.getName());
                                dragon.setCoordinates(d.getCoordinates());
                                dragon.setCreationDate(d.getCreationDate());
                                dragon.setAge(d.getAge());
                                dragon.setDescription(d.getDescription());
                                dragon.setWeight(d.getWeight());
                                dragon.setCharacter(d.getCharacter());
                                dragon.setHead(d.getHead());
                                a = 1;
                                JOptionPane.showMessageDialog(null, "Значения дракона успешно обновлены", "Message", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Этот дракон принадлежит другому пользователю", "Message", JOptionPane.INFORMATION_MESSAGE);
                                a = 1;
                            }
                        }
                    }
                    if (a == 0) {
                        JOptionPane.showMessageDialog(null, "Дракона с таким описанием и созданным пользователем " + t1.getText() + " не существует, поробуйте снова", "Message", JOptionPane.INFORMATION_MESSAGE);

                    }
            }
        return ts;
    }


    public TreeSet<Dragon> remove_lower(long id) throws SQLException {
        int a = 0;
        if (ts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Коллекция пуста, сначала добавьте элементы!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                ts.removeIf(dragon -> dragon.getId() < id & t1.getText().equals(dragon.getLogin()));
                JOptionPane.showMessageDialog(null, "Драконы с id меньше, чем " + id + ", принадлежащие " + t1.getText() + " удалены", "Message", JOptionPane.INFORMATION_MESSAGE);
                long l = 1;
                for (Dragon dragon : ts) {
                    dragon.setId(l);
                    l += 1;
                }
                a = 1;
            } catch (InputMismatchException exception) {
                System.out.println("Значение id должно быть представлено числом, поробуйте снова");
            }
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Драконов с id, меньше, чем " + id + " не существует, попробуйте снова", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        return ts;
    }

    public void print_field_descending_head() {

        if (ts.isEmpty()) {
            System.out.println("Коллекция пуста, сначала добавьте драконов!");
        } else {
            String s = "";
            Set set = new TreeSet(new HeadComparatop());
            ts.stream().forEach(dragon -> set.add(dragon.getHead())); // Stream API с лямбдой
            for (Object o : set) {
                s += o + "\n";
            }
            //  set.stream().forEach(o -> addString(o,s));  // Stream API с лямбдой
            JOptionPane.showMessageDialog(null, s, "Print_Head", JOptionPane.INFORMATION_MESSAGE);
        }
    }
  public  String[][] AddToTable(){
        String[][] data =null;
        for (Dragon dragon : ts){
        String [][] data1 = new String[][]{
                {""}

        };

        }

            return data;
    }
}