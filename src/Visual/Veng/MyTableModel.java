package Visual.Veng;

import Drago.Dragon;
import Visual.DragonTable;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static Managers.CommandManager.ts;
import static Managers.Commander.*;
public class MyTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    public int getRowCount() {
       return ts.size();
    }

    public int getColumnCount() {
        return 11;
    }
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0:
                return "Login";
            case 1:
                return "ID";
            case 2:
                return "Name";
            case 3:
                return "X";
            case 4:
                return "Y";
            case 5:
                return "Date";
            case 6:
                return "Description";
            case 7:
                return "Age";
            case 8:
                return "Weight";
            case 9:
                return "Character";
            case 10:
                return "Head";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<Dragon> d = new ArrayList<Dragon>();
            ts.stream().forEach(dragon -> d.add(dragon)); // Stream API с лямбдой
        Dragon dragon = d.get(rowIndex);
            switch (columnIndex){
                case 0:
                    return dragon.getLogin();
                case 1:
                    return String.valueOf(dragon.getId());
                case 2:
                    return dragon.getName();
                case 3:
                return dragon.getCoordinates().getX();
                case 4:
                    return dragon.getCoordinates().getY();
                case 5:
                    return dragon.getCreationDate();
                case 6:
                    return dragon.getDescription();
                case 7:
                    return dragon.getAge();
                case 8:
                    return dragon.getWeight();
                case 9:
                    return dragon.getCharacter();
                case 10:
                    return dragon.getHead();
            }

        return "";
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }
}
