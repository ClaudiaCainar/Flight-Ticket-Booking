package org.example.Presentation.View;

import org.example.Presentation.Controller.CAdmin;
import org.example.Presentation.Controller.CTraveler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class VAdmin {

    private JButton nou;
    private JButton sterge;
    private JButton update;
    private JFrame frame;
    private JTextField plecare;
    private JTextField destinatie;
    private JTextField nrZbor;
    private JButton cautaButon;
    private JButton cautaNrButon;
    private JButton veziTot;
    private JTable table;
    private JButton autUser;
    private JTable tableUser;
    private JTextField userId;
    private JTextField username;
    private JTextField pass;
    private JTextField tip;
    private JButton cautaId;

    public VAdmin() {
        CAdmin cAdmin = new CAdmin();
        CTraveler CTraveler = new CTraveler();
        this.frame = new JFrame("Admin");
        JPanel panou = new JPanel();

        panou.setLayout(null);
        panou.setBackground(new Color(245, 245, 245)); // Light gray background

        // Reduce the size and spacing of components
        JLabel plecareL = new JLabel("Departure");
        plecareL.setBounds(20, 20, 150, 20);

        this.plecare = new JTextField();
        plecare.setBounds(100, 20, 150, 25);

        JLabel destL = new JLabel("Arrival");
        destL.setBounds(20, 60, 150, 20);

        this.destinatie = new JTextField();
        destinatie.setBounds(100, 60, 150, 25);

        JLabel nrZbroL = new JLabel("Flight Number");
        nrZbroL.setBounds(20, 140, 150, 20);

        this.nrZbor = new JTextField();
        nrZbor.setBounds(100, 140, 150, 25);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Departure");
        model.addColumn("Arrival");
        model.addColumn("Flight Number");
        model.addColumn("Number of Seats");
        this.table = new JTable(model);
        table.setBackground(new Color(230, 255, 250)); // Light green-blue background

        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(102, 178, 255)); // Light blue header
        scrollPane.setBounds(280, 20, 350, 200);

        this.cautaButon = new JButton("Search by Location");
        cautaButon.setBounds(20, 100, 230, 25);

        this.cautaNrButon = new JButton("Search by Number");
        cautaNrButon.setBounds(20, 180, 230, 25);

        this.veziTot = new JButton("View All Flights");
        veziTot.setBounds(20, 220, 230, 30);

        JLabel id = new JLabel("User ID");
        id.setBounds(650, 20, 100, 20);

        this.userId = new JTextField();
        userId.setBounds(720, 20, 150, 25);

        JLabel usernameL = new JLabel("Username");
        usernameL.setBounds(650, 60, 100, 20);

        this.username = new JTextField();
        username.setBounds(720, 60, 150, 25);

        JLabel passL = new JLabel("Password");
        passL.setBounds(650, 100, 100, 20);

        this.pass = new JTextField();
        pass.setBounds(720, 100, 150, 25);

        JLabel tipL = new JLabel("User Type");
        tipL.setBounds(650, 140, 100, 20);

        this.tip = new JTextField();
        tip.setBounds(720, 140, 150, 25);

        DefaultTableModel modelUser = new DefaultTableModel();
        modelUser.addColumn("ID");
        modelUser.addColumn("Username");
        modelUser.addColumn("Password");
        modelUser.addColumn("Type");
        this.tableUser = new JTable(modelUser);
        tableUser.setBackground(new Color(230, 255, 250)); // Light green-blue background

        JScrollPane scrollPaneUser = new JScrollPane(tableUser);
        JTableHeader headerUser = tableUser.getTableHeader();
        headerUser.setBackground(new Color(102, 178, 255)); // Light blue header
        scrollPaneUser.setBounds(900, 20, 350, 200);

        autUser = new JButton("View Users");
        autUser.setBounds(720, 180, 150, 25);

        cautaId = new JButton("Search by ID");
        cautaId.setBounds(720, 220, 150, 25);

        update = new JButton("Update User");
        update.setBounds(720, 260, 150, 25);

        sterge = new JButton("Delete User");
        sterge.setBounds(720, 300, 150, 25);

        nou = new JButton("Add New User");
        nou.setBounds(720, 340, 150, 25);

        cautaButon.addActionListener(CTraveler.cauta(nrZbor,destinatie,plecare,table));
        cautaNrButon.addActionListener(CTraveler.zborCuNr(nrZbor,destinatie,plecare,table));
        veziTot.addActionListener(CTraveler.veziTot(nrZbor,destinatie,plecare,table));

        // Adjust frame size to fit the content
        panou.add(nou);
        panou.add(sterge);
        panou.add(update);
        panou.add(cautaId);
        panou.add(tipL);
        panou.add(tip);
        panou.add(id);
        panou.add(userId);
        panou.add(usernameL);
        panou.add(username);
        panou.add(passL);
        panou.add(pass);
        panou.add(autUser);
        panou.add(scrollPaneUser);
        panou.add(scrollPane);
        panou.add(cautaButon);
        panou.add(cautaNrButon);
        panou.add(plecareL);
        panou.add(destL);
        panou.add(nrZbroL);
        panou.add(nrZbor);
        panou.add(plecare);
        panou.add(destinatie);
        panou.add(veziTot);

        frame.setSize(1300, 400);
        frame.setVisible(true);
        frame.setContentPane(panou);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        autUser.addActionListener(cAdmin.autUser(tableUser, tip));
        cautaId.addActionListener(cAdmin.cautaId(userId, tableUser));
        update.addActionListener(cAdmin.update(userId, username, pass, tip));
        sterge.addActionListener(cAdmin.sterge(userId));
        nou.addActionListener(cAdmin.nou(username, pass, tip));
    }
}
