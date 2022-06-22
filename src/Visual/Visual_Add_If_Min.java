package Visual;

import Drago.Dragon;
import Managers.CommandManager;
import commands.Add_if_max;
import commands.Add_if_min;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Visual.EnterVisual.t1;

public class Visual_Add_If_Min extends JFrame {
    CommandManager cm;
    long id;
    JTextField Name, X, Y, Desc, Age, Weight, Head;
    JComboBox comboBox;

    public Visual_Add_If_Min(CommandManager cm, long id) {
        this.cm = cm;
        this.id = id;
        setTitle("Добавить дракона");
        Box box1 = Box.createHorizontalBox();
        JLabel NameLabel = new JLabel("Имя дракона:");
        Name = new JTextField(15);
        box1.add(NameLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(Name);

        Box box2 = Box.createHorizontalBox();
        JLabel XLabel = new JLabel("Координата X:");
        X = new JTextField(15);
        box2.add(XLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(X);

        Box box3 = Box.createHorizontalBox();
        JLabel YLabel = new JLabel("Координата Y:");
        Y = new JTextField(15);
        box3.add(YLabel);
        box3.add(Box.createHorizontalStrut(6));
        box3.add(Y);

        Box box4 = Box.createHorizontalBox();
        JLabel DescLabel = new JLabel("Описание дракона:");
        Desc = new JTextField(15);
        box4.add(DescLabel);
        box4.add(Box.createHorizontalStrut(6));
        box4.add(Desc);

        Box box5 = Box.createHorizontalBox();
        JLabel AgeLabel = new JLabel("Возраст дракона:");
        Age = new JTextField(15);
        box5.add(AgeLabel);
        box5.add(Box.createHorizontalStrut(6));
        box5.add(Age);

        Box box6 = Box.createHorizontalBox();
        JLabel WeightLabel = new JLabel("Вес дракона:");
        Weight = new JTextField(15);
        box6.add(WeightLabel);
        box6.add(Box.createHorizontalStrut(6));
        box6.add(Weight);

        Box box7 = Box.createHorizontalBox();
        JLabel CharLabel = new JLabel("Характер дракона:");
        String[] elements = {
                "Cunning",
                "Evil",
                "Chaotic"
        };
        comboBox = new JComboBox(elements);
        box7.add(CharLabel);
        box7.add(Box.createHorizontalStrut(6));
        box7.add(comboBox);

        Box box8 = Box.createHorizontalBox();
        JLabel HeadLabel = new JLabel("Количество глаз дракона:");
        Head = new JTextField(15);
        box8.add(HeadLabel);
        box8.add(Box.createHorizontalStrut(6));
        box8.add(Head);

        Box box9 = Box.createHorizontalBox();
        JButton button1 = new JButton("Добавить дракона");
        JButton button2 = new JButton("<<");
        box9.add(button1);
        box9.add(Box.createHorizontalStrut(6));
        box9.add(button2);

        NameLabel.setPreferredSize(HeadLabel.getPreferredSize());
        XLabel.setPreferredSize(HeadLabel.getPreferredSize());
        YLabel.setPreferredSize(HeadLabel.getPreferredSize());
        DescLabel.setPreferredSize(HeadLabel.getPreferredSize());
        AgeLabel.setPreferredSize(HeadLabel.getPreferredSize());
        WeightLabel.setPreferredSize(HeadLabel.getPreferredSize());
        CharLabel.setPreferredSize(HeadLabel.getPreferredSize());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box5);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box6);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box7);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box8);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box9);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        button1.addActionListener(new AddButton());
        button2.addActionListener(new BackButton());
    }

    private class AddButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            VisualChecker v = new VisualChecker();
            int index = comboBox.getSelectedIndex();
            Dragon dragon = new Dragon(id, v.VName(Name.getText()), v.VCoord(X.getText(), Y.getText()), v.CREATIONDATE(),
                    v.VDesc(Desc.getText()), v.Vage(Age.getText()), v.Vweight(Weight.getText()), v.VChar(index), v.VdragonHead(Head.getText()), t1.getText());
            if (v.Errorcounter() == false) {
                v.ErrorMessage();
            } else {
                Add_if_min add_if_min = new Add_if_min(cm, dragon);
                try {
                    add_if_min.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class BackButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            VisualIfMin visualIfMin = new VisualIfMin(cm);
            setVisible(false);
        }
    }
}
