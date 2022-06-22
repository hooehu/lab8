import java.io.IOException;
import java.sql.*;

import Drago.DragoPicture;
import Managers.*;
import Users.Auth;
import Users.AuthCheck;
import Users.Enter;
import Users.UserBDCreator;

import Visual.MainMenu;
import Visual.Ru.SimpleGuiRu;

import javax.swing.*;


public class Main {
    public static void main(String args[]) {
        //AuthCheck authCheck = new AuthCheck();
     //   authCheck.Check();
        CommandManager cm = new CommandManager();
        Commander c = new Commander(cm);
            cm.read();
     //   try {
       //     c.start();
      //  } catch (IOException e) {
        //    e.printStackTrace();

        SimpleGuiRu app = new SimpleGuiRu();
        //app.setVisible(true);

        }
    }









