package baze.model.implementation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Clause {
    private String name;

    public Clause(String name) {
        this.name = name;
    }

    public void fillOut(String[] lines, int l, int r) {

    }
}
