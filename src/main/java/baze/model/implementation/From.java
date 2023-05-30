package baze.model.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class From extends Clause {
    // Moze da ima join
    public From(String name) {
        super(name);
    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
    }
}
