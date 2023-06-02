package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//Ovo je samo obican string jer ne moze da se spoji lista stringova i operatora
public class ColumnString extends Oprt{
    private String columnName;

    public ColumnString(String columnName) {
        this.columnName = columnName;
    }

    //Da mu do operation nista ne radi
    @Override
    public void doOperation(String[] line, int c) {

    }

    @Override
    public String toString() {
        return "ColumnString{" +
                "columnName='" + columnName + '\'' +
                '}';
    }
}
