package baze.gui.view;


import baze.gui.controller.ReadTextAction;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame{

    private static MainFrame instance = null;
    private JButton btnRun;
    private JTextArea textArea;
    private JTable table;

    private MainFrame() {

    }

    private void init() {
        //Omogucava kreiranje GUI komponente iz paketa na bilo kojoj platformi(Windows, IOS, Linux, ...)
        Toolkit kit = Toolkit.getDefaultToolkit();
        //Dobijamo dimenzije racunara za visinu i sirinu
        Dimension screenSize = kit.getScreenSize();
        //Na osnovu dimenzija znamo dimenzije bilo kog kompjutera i uvek mozemo na isto mesto da im postavimo aplikaciju
        //bez obzira na rezoluciju
        int screenHeight = (int) screenSize.getHeight();
        int screenWidth = (int) screenSize.getWidth();
        this.setSize(screenWidth / 2, screenHeight / 2 + 100);
        this.setLocationRelativeTo(null);//postavlja na sredini ekrana aplikaciju
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close operacija(onaj x desno gore) zatvara aplikaciju skroz
        this.setTitle("DataBase");

        btnRun = new JButton("Run");
        btnRun.addActionListener(new ReadTextAction());
        textArea = new JTextArea((int) (this.getSize().getHeight()/20), (int) (this.getSize().getWidth()/20));

        table = new JTable();
        table.setFillsViewportHeight(true);
        this.add(btnRun, BorderLayout.NORTH);
        this.add(new JScrollPane(textArea));
        this.add(new JScrollPane(table), BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static MainFrame getInstance(){
        if (instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }
}
