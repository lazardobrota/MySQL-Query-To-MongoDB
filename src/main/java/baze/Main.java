package baze;

import baze.gui.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        AppCore appCore = new AppCore();
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().setAppCore(appCore);

        appCore.readDataFromTable("departments"); // poziva metodu iz appcora gde u tabeli ko
    }
}
