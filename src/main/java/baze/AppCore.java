package baze;

import baze.database.Database;
import baze.database.MyMongodatabase;
import baze.database.settings.Settings;
import baze.database.settings.SettingsImplementation;
import baze.gui.table.TableModel;
import baze.gui.view.MainFrame;
import baze.model.validator.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter
public class AppCore {
    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private DefaultTreeModel defaultTreeModel;

    public AppCore() {
        this.settings = initSettings(); // popunjuje hashmapu informacijama
        this.database =  new MyMongodatabase(this.settings);
        this.tableModel = new TableModel();
    }

    private Settings initSettings() {

        Settings settingsImplementation = new SettingsImplementation();

        //Dodaje u heshmapu stvari koju ima settingsImplementation unutar sebe
        //addParametar poziva tu hashmapu i dodaje joj parametre
        settingsImplementation.addParameter("mysql_ip", Constants.MYSQL_IP);
        settingsImplementation.addParameter("mysql_database", Constants.MYSQL_DATABASE);
        settingsImplementation.addParameter("mysql_username", Constants.MYSQL_USERNAME);
        settingsImplementation.addParameter("mysql_password", Constants.MYSQL_PASSWORD);
        return settingsImplementation;
    }

    //Unutar controllera preko mainframe ce pozivati ovu funkciju
    public void readDataFromTable(String fromTable){
        tableModel.setRows(this.database.getDataFromTable(fromTable));
    }
}
