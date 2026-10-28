package org.example.Presentation.Controller;


import org.example.Service.IFlightService;
import org.example.Service.Implementation.FlightServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CTraveler {
  private IFlightService IFlightService =new FlightServiceImpl();
  public ActionListener zborCuNr(JTextField nrZbor,JTextField destinatie,JTextField plecare,JTable table) {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String z = IFlightService.cautaZbor(nrZbor.getText());
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.setRowCount(0);
                if (z == null) {
                    model.addRow(new Object[]{"NU EXISTA", "ZBORUL CU", "NR " + nrZbor.getText()});
                } else {
                    String[] s=z.split(" ");
                    model.addRow(new Object[]{s[0], s[1], s[2], s[3]});
                }

            }
        };
        return e;
    }
    public ActionListener veziTot(JTextField nrZbor, JTextField destinatie, JTextField plecare, JTable table) {
            ActionListener e = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> zboruri = IFlightService.toateZborurile();

                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    model.setRowCount(0);
                    if (zboruri.size() == 0) {
                        model.addRow(new Object[]{"NU", "EXISTA", "ZBORURI"});
                    } else {
                        for (String z : zboruri) {
                            String[] s=z.split(" ");

                            model.addRow(new Object[]{s[1], s[2], s[0], s[3]});
                        }
                    }
                }
            };
            return e;
        }
    public ActionListener cauta(JTextField nrZbor, JTextField destinatie, JTextField plecare, JTable table) {
            ActionListener e = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> zboruri = FlightServiceImpl.splitFlights( IFlightService.cautaZboruri(plecare.getText(), destinatie.getText()));

                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    model.setRowCount(0);
                    if (zboruri.size() == 0) {
                        model.addRow(new Object[]{"NU", "EXISTA", "ZBORURI"});
                    } else {
                        for (String z : zboruri) {
                            String s[] = z.split(" ");
                            model.addRow(new Object[]{s[0], s[1], s[2], s[3]});
                        }
                    }
                }
            };
            return e;
        }


}
