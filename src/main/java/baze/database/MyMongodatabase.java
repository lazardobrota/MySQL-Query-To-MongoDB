package baze.database;

import baze.data.Row;
import baze.database.settings.Settings;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MyMongodatabase implements Database{
    private Settings settings; // cuva informacije o bazi, ip, username, ...
    private MongoClient connection; // iz biblioteke

    public MyMongodatabase(Settings settings) {
        this.settings = settings;
    }

    //Inicijalizacija konekicje prikuplja sve informacije o data bazi da bi znao koju da otvori
    private void initConnection(){
        String ip = (String) settings.getParameter("mysql_ip");
        String database = (String) settings.getParameter("mysql_database");
        String username = (String) settings.getParameter("mysql_username");
        String password = (String) settings.getParameter("mysql_password");

        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray()); // Uzima kredencijale za mongo bazu
        connection = new MongoClient(new ServerAddress(ip, 27017), Arrays.asList(credential)); // konektuje se na datu bazu
    }

    //Zatvara konekciju sa data bazom, JAKOOOO BITNO DA SE OVO STALNO POZIVA NAKON OTVARANJA BAZE!!!!!!!!
    private void closeConnection(){
        try{
            connection.close();
        }
        catch (MongoException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }

    @Override
    public List<Row> getDataFromTable(String from) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();

            //Uspostavlja konekicju
            MongoDatabase database = connection.getDatabase((String) settings.getParameter("mysql_database")); // bp_tim47

            //Ovo je ko ResultSet za sql
            //Umesto onog u agregate, bice mapper koji sve to radi za nas
            MongoCursor<org.bson.Document> cursor = database.getCollection("employees").aggregate(
                    Arrays.asList(
                            org.bson.Document.parse("{\n" +
                                    "  $match: {first_name: \"Steven\", last_name: \"King\"}\n" +
                                    "}"),
                            org.bson.Document.parse("{\n" +
                                    "  $lookup: {\n" +
                                    "    from: \"employees\",\n" +
                                    "    localField: \"department_id\",\n" +
                                    "    foreignField: \"department_id\",\n" +
                                    "    as: \"employeesInTheSameDepartment\"\n" +
                                    "  }\n" +
                                    "}"),
                            org.bson.Document.parse("{ $unwind: \"$employeesInTheSameDepartment\" }"),
                            Document.parse("{ $project: {\n" +
                                    "    \"employeesInTheSameDepartment.first_name\": 1,\n" +
                                    "    \"employeesInTheSameDepartment.last_name\": 1\n" +
                                    "  }\n" +
                                    "}")
                    )
            ).iterator();

            while (cursor.hasNext()) {
                Row row = new Row(); // Novi red za novu informaciju
                row.setName("hello"); // to treba from da bude

                Document document = cursor.next();//Uzima taj dokument informacija
                Packer packer = new Packer(document.toJson());

                packer.translate(); // translira dokument u hash mapu
                //Prolazi kroz sve kolone
                for (int i = 0; i < packer.getColumnNames().size(); i++) {
                    //Dodaje ime kolone i value koji taj kolona ima
                    row.addField(packer.getColumnNames().get(i), packer.getValues().get(i));
                }
                //System.out.println(document.toJson());
                rows.add(row);//dodaje u niz  redova i ovaj red
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }
}