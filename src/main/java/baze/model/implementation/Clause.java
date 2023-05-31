package baze.model.implementation;

import baze.model.implementation.operators.Oprt;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class Clause implements AClause{
    private String name;

    private List<Oprt> operators;
    private Map<String, Oprt> hashMap;

    public Clause(String name) {
        this.name = name;
        operators = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    public abstract void fillOut(String[] lines, int l, int r);
}
