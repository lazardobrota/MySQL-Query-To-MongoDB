package baze.data;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Row {
    private String name; // odakle dolazi
    private Map<String, Object> fields; // ime kolone i objekat koji treba da zalepi u tu kolonu u ovom redu


    public Row() {
        this.fields = new HashMap<>();
    }

    public void addField(String fieldName, Object value) {
        this.fields.put(fieldName, value);
    }

    public void removeField(String fieldName) {
        this.fields.remove(fieldName);
    }
}
