package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter

//Ovo je samo obican string jer ne moze da se spoji lista stringova i operatora
public class ColumnString extends Oprt {

    //SAMO ColumnString koristi VALUE
    public ColumnString(String columnName) {
        this.value = columnName;
    }

    //Da mu do operation nista ne radi
    @Override
    public void doOperation(String[] line, int c) {

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColumnString))
            return false;
        ColumnString that = (ColumnString) obj;


        if (this.value.equals(that.value))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "ColumnString{" +
                "value='" + value + '\'' +
                '}';
    }
}
