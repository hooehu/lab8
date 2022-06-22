package Visual;

import Visual.Veng.MyTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

import static Visual.EnterVisual.t1;

public class DragonTable extends JFrame {
    public DragonTable(){
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TableModel model = new MyTableModel();
        JTable table = new JTable(model);
        getContentPane().add(new JScrollPane(table));
        setPreferredSize(new Dimension(700, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
