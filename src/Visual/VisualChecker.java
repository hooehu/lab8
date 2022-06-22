package Visual;

import Drago.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class VisualChecker {
    String message = "";
    int a = 0;

    public String VName(String name) {
        if (name.equals("")) {
            message += "Неверное имя! \n";
            a += 1;
        }
        return name;
    }

    public Coordinates VCoord(String X, String Y) {
        int x = 0;
        long y = 0;
        try {
            x = Integer.valueOf(X);
            if (x < Coordinates.min_x) {
                message += "Значение x должно быть больше, чем" + Coordinates.min_x + " \n";
                a += 1;
            }
        } catch (InputMismatchException exception) {
            message += "Значение y должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        } catch (NumberFormatException exception) {
            message += "Значение x должно быть представлено числом \n";
            a += 1;
        }
        try{
            y = Long.valueOf(Y);
            if (y < Coordinates.min_y) {
                message += "Значение x должно быть больше, чем" + Coordinates.min_y + " \n";
                a += 1;
            }
        } catch (InputMismatchException exception) {
            message += "Значение y должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        } catch (NumberFormatException exception) {
            message += "Значение x должно быть представлено числом \n";
            a += 1;
        }
        return new Coordinates(x, y);
    }
    public LocalDate CREATIONDATE(){
        LocalDate creationDate = LocalDate.now();
        return creationDate;
    }
    public String VDesc(String description) {
        if (description.equals("")) {
            message += "Некорректное описание \n";
            a += 1;
        }
        return description;
    }

    public int Vage(String Age) {
        int age = 0;
        try {
            age = Integer.valueOf(Age);
            if (age <= 0) {
                message += "Ошибка! Число должно быть больше, чем 0 \n";
                a += 1;
            }
        } catch (InputMismatchException exception) {
            message += "Значение age должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        } catch (NumberFormatException exception) {
            message += "Значение возраста должно быть представлено числом \n";
            a += 1;
        }
        return age;
    }

    public int Vweight(String Weight) {
        int weight= 0;
        try {
            weight = Integer.valueOf(Weight);
            if (weight <= 0) {
                message += "Ошибка! Число должно быть больше, чем " + 0 + " \n";
                a += 1;
            }
        } catch (InputMismatchException exception) {
            message += "Значение weight должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        } catch (NumberFormatException exception) {
            message += "Значение веса должно быть представлено числом \n";
            a += 1;
        }
        return weight;
    }

    public DragonCharacter VChar(int index) {
        DragonCharacter dragonCharacter;
        while (true) {

            if (index == 0) {
                dragonCharacter = DragonCharacter.CUNNING;
                break;
            } else if (index == 1) {
                dragonCharacter = DragonCharacter.EVIL;
                break;
            } else if (index == 2) {
                dragonCharacter = DragonCharacter.CHAOTIC;
                break;
            }

        }
        return dragonCharacter;
    }

    public double Veyes(double eyes) {
        try {
        } catch (InputMismatchException exception) {
            message += "Значение eyes должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        }
        return eyes;
    }
    public DragonHead VdragonHead(String Eyes) {
        double eyes =0.0;
        try {
          eyes  = Double.valueOf(Eyes);
        } catch (InputMismatchException exception) {
            message += "Значение eyes должно быть представлено числом \n";
            a += 1;
        } catch (NullPointerException exception) {
            message += "Поле не может быть null \n";
            a += 1;
        } catch (NumberFormatException exception){
            message += "Количество глаз должно быть представлено числом \n";
            a+=1;
        }

        return new DragonHead(eyes);
    }

    public boolean Errorcounter() {
        if (a > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void ErrorMessage() {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

}
