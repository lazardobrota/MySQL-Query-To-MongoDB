package baze.gui.controller;

import baze.model.adapter.ClauseAdapter;
import baze.model.adapter.mapper.Mapper;
import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.validator.Validator;
import baze.gui.view.MainFrame;
import baze.model.implementation.SQLQuery;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ReadTextAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        SQLQuery sqlQuery = new SQLQuery();
        sqlQuery.strToObj(MainFrame.getInstance().getTextArea().getText()); // prosledjuje mu tekst koji je napisan u tekst area

        //Ako nista nije napisao
        /*TODO Odkomentarisi kasnije ovo, samo radi testiranja stvari je zakomentarisano
        if (sqlQuery.getClaues().isEmpty())
            return;

         */
        Validator validator = new Validator(sqlQuery);
        if (!validator.checkRules()) {
            MainFrame.getInstance().errorMessage(validator.getMessage());
        }
        List<ClauseAdapter> clauseAdapters = new ArrayList<>();
        for (int i = 0 ;i < sqlQuery.getClaues().size(); i++) {
            Clause clause = (Clause) sqlQuery.getClaues().get(i);
            clauseAdapters.add((ClauseAdapter) AdapterFactoryUtils.getFactory(clause).getAdapter(clause));
        }

        Mapper mapper = new Mapper(clauseAdapters);
        mapper.combineClauses();
        MainFrame.getInstance().getAppCore().readDataFromTable(mapper.getFrom());
        System.out.println(clauseAdapters);
    }
}
