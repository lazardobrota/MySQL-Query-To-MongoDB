package baze.database.settings;

import java.util.HashMap;
import java.util.Map;

// Cuva informacije o bazi, ip, username, ...
public class SettingsImplementation implements Settings{
    private Map parameters;

    public SettingsImplementation() {
        parameters = new HashMap<>();
    }

    // uzima parametar(ip, username, pass, ...) iz hashmape
    public Object getParameter(String parameter) {
        return  this.parameters.get(parameter);
    }

    //Dodaje parametar u heshmapu
    public void addParameter(String parameter, Object value) {
        this.parameters.put(parameter, value);
    }
}
