package org.example.Presentation.controller;

import org.example.Presentation.View.VTraveler;
import org.example.Presentation.View.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CStart {


    public ActionListener deschideLogin(JFrame frame) {
        ActionListener e=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login login=new Login();
            }
        };
        return e;
    }

    public ActionListener deschideNormal(JFrame frame) {
    ActionListener e=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            VTraveler login=new VTraveler();
            }
    };
    return e;
    }
}
