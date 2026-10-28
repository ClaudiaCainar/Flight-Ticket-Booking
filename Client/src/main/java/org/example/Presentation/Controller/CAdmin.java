package org.example.Presentation.Controller;

import org.example.Service.Factory.IUserServiceFactory;
import org.example.Service.Factory.UserServiceFactory;
import org.example.Service.IUserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CAdmin extends CTraveler {
    private IUserService IUserService;

    public CAdmin() {
        IUserServiceFactory factory = new UserServiceFactory();
        this.IUserService = factory.createUserService();
    }

    public ActionListener autUser(JTable table, JTextField tip) {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> useri = null;
                try {
                    useri = IUserService.useriAut(tip.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                if (useri.size() == 0) {
                    model.addRow(new Object[]{"NU", "EXISTA", "USERI"});
                } else {
                    for (String user : useri) {
                        String[] z = user.split(" ");
                        model.addRow(new Object[]{z[0], z[1], z[2], z[3]});
                    }
                }
            }
        };
        return e;
    }

    public ActionListener cautaId(JTextField id, JTable table) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = null;
                try {
                    aux = IUserService.cautadupaID(id.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                String s[] = aux.split(" ");
                if (s[1].equals("null")) {
                    model.addRow(new Object[]{"NU EXISTA", "USERUL CU", "ID ", id.getText()});
                } else {
                    model.addRow(new Object[]{s[0], s[1], s[2], s[3]});
                }
            }
        };
    }

    public ActionListener update(JTextField id, JTextField username, JTextField pass, JTextField TIP) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IUserService.update(id.getText() + " " + username.getText() + " " + pass.getText() + " " + Integer.parseInt(TIP.getText()));
            }
        };
    }

    public ActionListener sterge(JTextField id) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IUserService.stergeUser(id.getText());
            }
        };
    }

    public ActionListener nou(JTextField username, JTextField pass, JTextField TIP) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IUserService.newUser(username.getText(), pass.getText(), Integer.parseInt(TIP.getText()));
                JOptionPane.showMessageDialog(null, "User adaugat cu succes!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
}
