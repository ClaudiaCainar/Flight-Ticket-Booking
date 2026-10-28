package org.example.Presentation.View;

import org.example.Presentation.Controller.CTraveler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class VTraveler {

    private JFrame frame;
    private JTextField plecare;
    private JTextField destinatie;
    private JTextField nrZbor;
    private JButton cautaButon;
    private JButton cautaNrButon;
    private JButton veziTot;
    private JTable table;

    public VTraveler() {

        this.frame = new JFrame("Passenger");
        JPanel panou = new JPanel();

        panou.setLayout(null);
        panou.setBackground(new Color(245, 245, 245)); // Light gray background

        // Adjust component sizes and positions for a more compact layout
        JLabel plecareL = new JLabel("Departure");
        plecareL.setBounds(20, 20, 100, 20);

        this.plecare = new JTextField();
        plecare.setBounds(120, 20, 150, 25);

        JLabel destL = new JLabel("Arrival");
        destL.setBounds(20, 60, 100, 20);

        this.destinatie = new JTextField();
        destinatie.setBounds(120, 60, 150, 25);

        JLabel nrZborL = new JLabel("Flight Number");
        nrZborL.setBounds(20, 140, 100, 20);

        this.nrZbor = new JTextField();
        nrZbor.setBounds(120, 140, 150, 25);

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
        scrollPane.setBounds(300, 20, 350, 300);

        this.cautaButon = new JButton("Search by Location");
        cautaButon.setBounds(20, 100, 250, 25);

        this.cautaNrButon = new JButton("Search by Number");
        cautaNrButon.setBounds(20, 180, 250, 25);

        this.veziTot = new JButton("View All Flights");
        veziTot.setBounds(20, 220, 250, 30);

        CTraveler CTraveler = new CTraveler();

        cautaNrButon.addActionListener(CTraveler.zborCuNr(nrZbor, destinatie, plecare, table));
        veziTot.addActionListener(CTraveler.veziTot(nrZbor, destinatie, plecare, table));
        cautaButon.addActionListener(CTraveler.cauta(nrZbor, destinatie, plecare, table));

        panou.add(scrollPane);
        panou.add(cautaButon);
        panou.add(cautaNrButon);
        panou.add(plecareL);
        panou.add(destL);
        panou.add(nrZborL);
        panou.add(nrZbor);
        panou.add(plecare);
        panou.add(destinatie);
        panou.add(veziTot);

        // Adjust frame size to fit the new compact layout
        frame.setSize(700, 400);
        frame.setVisible(true);
        frame.setContentPane(panou);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
