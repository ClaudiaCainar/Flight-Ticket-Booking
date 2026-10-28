package org.example.Presentation.View;

import org.example.Presentation.Controller.CEmployee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class VEmployee {
    private JButton updateZbor;
    private JButton adaugaZbor;
    private JFrame frame;
    private JTextField plecare;
    private JTextField destinatie;
    private JTextField nrZbor;
    private JButton cautaButon;
    private JButton cautaNrButon;
    private JButton stergeZbor;
    private JButton veziTot;
    private JTable table;
    private JTextField nrBilet;
    private JButton updateBilet;
    private JButton adaugaBilet;
    private JTextField nume;
    private JTextField nrZborBilet;
    private JButton cautaNumeButon;
    private JButton cautaNrBiletButon;
    private JButton stergeBilet;
    private JButton veziToateBiletele;
    private JButton export;
    private JButton statisticsButton;
    private JTable tableBilete;

    public VEmployee() {
        this.frame = new JFrame("Employee");
        JPanel panou = new JPanel();
        panou.setLayout(null);
        panou.setBackground(new Color(240, 248, 255)); // Light blue background

        // Departure Section
        JLabel plecareL = new JLabel("Departure:");
        plecareL.setBounds(40, 40, 100, 25);
        plecare = new JTextField();
        plecare.setBounds(150, 40, 200, 25);

        // Arrival Section
        JLabel destL = new JLabel("Arrival:");
        destL.setBounds(40, 80, 100, 25);
        destinatie = new JTextField();
        destinatie.setBounds(150, 80, 200, 25);

        // Flight Number Section
        JLabel nrZbroL = new JLabel("Flight Number:");
        nrZbroL.setBounds(40, 120, 100, 25);
        nrZbor = new JTextField();
        nrZbor.setBounds(150, 120, 200, 25);

        // Buttons for Flights
        cautaButon = new JButton("Search by Route");
        cautaButon.setBounds(40, 160, 150, 30);
        stylizeButton(cautaButon);

        cautaNrButon = new JButton("Search by Number");
        cautaNrButon.setBounds(200, 160, 150, 30);
        stylizeButton(cautaNrButon);

        updateZbor = new JButton("Update Flight");
        updateZbor.setBounds(40, 200, 150, 30);
        stylizeButton(updateZbor);

        stergeZbor = new JButton("Delete Flight");
        stergeZbor.setBounds(200, 200, 150, 30);
        stylizeButton(stergeZbor);

        veziTot = new JButton("All Flights");
        veziTot.setBounds(120, 240, 150, 30);
        stylizeButton(veziTot);

        adaugaZbor = new JButton("Add Flight");
        adaugaZbor.setBounds(120, 280, 150, 30);
        stylizeButton(adaugaZbor);

        export = new JButton("Export Data");
        export.setBounds(120, 320, 150, 30);
        stylizeButton(export);

        // Flight Table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Departure");
        model.addColumn("Arrival");
        model.addColumn("Flight Number");
        model.addColumn("Seats");
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JTableHeader header = table.getTableHeader();
        scrollPane.setColumnHeaderView(header);
        scrollPane.setBounds(400, 40, 500, 300);

        // Ticket Section
        JLabel numeL = new JLabel("Passenger Name:");
        numeL.setBounds(40, 380, 120, 25);
        nume = new JTextField();
        nume.setBounds(160, 380, 200, 25);

        JLabel nrBiletL = new JLabel("Ticket Number:");
        nrBiletL.setBounds(40, 420, 120, 25);
        nrBilet = new JTextField();
        nrBilet.setBounds(160, 420, 200, 25);

        // Buttons for Tickets
        cautaNumeButon = new JButton("Search by Name");
        cautaNumeButon.setBounds(40, 460, 150, 30);
        stylizeButton(cautaNumeButon);

        cautaNrBiletButon = new JButton("Search by Ticket");
        cautaNrBiletButon.setBounds(200, 460, 150, 30);
        stylizeButton(cautaNrBiletButon);

        updateBilet = new JButton("Update Ticket");
        updateBilet.setBounds(40, 500, 150, 30);
        stylizeButton(updateBilet);

        stergeBilet = new JButton("Delete Ticket");
        stergeBilet.setBounds(200, 500, 150, 30);
        stylizeButton(stergeBilet);

        veziToateBiletele = new JButton("All Tickets");
        veziToateBiletele.setBounds(120, 540, 150, 30);
        stylizeButton(veziToateBiletele);

        adaugaBilet = new JButton("Add Ticket");
        adaugaBilet.setBounds(120, 580, 150, 30);
        stylizeButton(adaugaBilet);

        statisticsButton = new JButton("View Statistics");
        statisticsButton.setBounds(120, 620, 150, 30);  // Set position and size
        stylizeButton(statisticsButton);  // Apply styling

        // Add the Statistics button to the panel
        panou.add(statisticsButton);

        // Ticket Table
        DefaultTableModel modelBilet = new DefaultTableModel();
        modelBilet.addColumn("Ticket Number");
        modelBilet.addColumn("Passenger Name");
        modelBilet.addColumn("Flight Number");
        tableBilete = new JTable(modelBilet);
        JScrollPane scrollPaneBilete = new JScrollPane(tableBilete);
        JTableHeader headerBilete = tableBilete.getTableHeader();
        scrollPaneBilete.setColumnHeaderView(headerBilete);
        scrollPaneBilete.setBounds(400, 380, 500, 300);

        // Add components to panel
        panou.add(plecareL);
        panou.add(plecare);
        panou.add(destL);
        panou.add(destinatie);
        panou.add(nrZbroL);
        panou.add(nrZbor);
        panou.add(cautaButon);
        panou.add(cautaNrButon);
        panou.add(updateZbor);
        panou.add(stergeZbor);
        panou.add(veziTot);
        panou.add(adaugaZbor);
        panou.add(export);
        panou.add(scrollPane);
        panou.add(numeL);
        panou.add(nume);
        panou.add(nrBiletL);
        panou.add(nrBilet);
        panou.add(cautaNumeButon);
        panou.add(cautaNrBiletButon);
        panou.add(updateBilet);
        panou.add(stergeBilet);
        panou.add(veziToateBiletele);
        panou.add(adaugaBilet);
        panou.add(scrollPaneBilete);

        // Frame settings
        frame.setResizable(false);
        frame.setSize(950, 700);
        frame.setVisible(true);
        frame.setContentPane(panou);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CEmployee CEmployee = new CEmployee(nrZbor, destinatie, plecare, nume, nrBilet, nrZborBilet, table, tableBilete);

        statisticsButton.addActionListener(CEmployee.statistici());
        adaugaZbor.addActionListener(CEmployee.adaugaZbor());
        cautaNrButon.addActionListener(CEmployee.zborCuNr());
        stergeZbor.addActionListener(CEmployee.stergeZbor());
        veziTot.addActionListener(CEmployee.veziTot());
        updateZbor.addActionListener(CEmployee.updateZbor());
        export.addActionListener(CEmployee.export());
        cautaButon.addActionListener(CEmployee.cauta());
        adaugaBilet.addActionListener(CEmployee.adaugaBilet());
        cautaNrBiletButon.addActionListener(CEmployee.cautaNrBilet());
        stergeBilet.addActionListener(CEmployee.stergeBilet());
        veziToateBiletele.addActionListener(CEmployee.veziToateBiletele());
        updateBilet.addActionListener(CEmployee.updateBilet());
    }

    private void stylizeButton(JButton button) {
        button.setBackground(new Color(100, 149, 237)); // Cornflower blue
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}
