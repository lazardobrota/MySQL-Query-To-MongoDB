package baze.gui.table;

import baze.data.Row;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TableModel extends DefaultTableModel {

    private List<Row> rows;

    private void updateModel(){
        int columnCount = 0;
        Vector columnVector = new Vector<>();

        if (!rows.isEmpty()) {
            columnCount = rows.get(0).getFields().keySet().size();

            columnVector = DefaultTableModel.convertToVector(rows.get(0).getFields().keySet().toArray());
        }
        
        Vector dataVector = new Vector(columnCount);

        for (int i=0; i<rows.size(); i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
        }
        setDataVector(dataVector, columnVector);
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
        updateModel();
    }
}
