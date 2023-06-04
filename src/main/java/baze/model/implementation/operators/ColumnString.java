package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//Ovo je samo obican string jer ne moze da se spoji lista stringova i operatora
public class ColumnString extends Oprt{

    //SAMO ColumnString koristi VALUE
    public ColumnString(String columnName) {
        this.value = columnName;
    }

    //Da mu do operation nista ne radi
    @Override
    public void doOperation(String[] line, int c) {

    }

    @Override
    public String toString() {
        return "ColumnString{" +
                "value='" + value + '\'' +
                '}';
    }
}
