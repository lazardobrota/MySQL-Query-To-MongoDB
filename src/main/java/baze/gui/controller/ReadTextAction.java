package baze.gui.controller;

import baze.gui.validator.Validator;
import baze.gui.view.MainFrame;
import baze.model.implementation.SQLQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReadTextAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        SQLQuery sqlQuery = new SQLQuery();
        sqlQuery.strToObj(MainFrame.getInstance().getTextArea().getText()); // prosledjuje mu tekst koji je napisan u tekst area
        Validator validator = new Validator(sqlQuery);
        validator.checkRules();
    }
}
