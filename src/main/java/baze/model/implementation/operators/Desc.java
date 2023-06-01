package baze.model.implementation.operators;

//Opadajuce
public class Desc extends Oprt{

    private boolean isDesc;

    public Desc(boolean isDesc) {
        this.isDesc = isDesc;
    }

    public void setDesc(boolean desc) {
        isDesc = desc;
    }

    @Override
    public String toString() {
        return "Desc{" +
                "isDesc=" + isDesc +
                '}';
    }
}
