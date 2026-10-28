package org.example.Presentation.View;

import org.example.Presentation.Controller.Language.Language;
import org.example.Presentation.controller.CStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {

    private JFrame frame;
    private JComboBox lang;
    private Language language;
    private String aux="Ro";
    JButton normalButon, loginButton;

    public Start() {
        language = new Language();
        language.setLanguage(aux);
        frame = new JFrame("Home");
        frame.setSize(300, 300);
        JPanel panou = new JPanel();

        panou.setLayout(null);
        panou.setBackground(new Color(126, 217, 252));
        loginButton = new JButton("Logare");
        loginButton.setBounds(30, 150, 120, 40);
        CStart cStart = new CStart();

        normalButon = new JButton("Calator");
        normalButon.setBounds(150, 150, 120, 40);

        panou.add(loginButton);
        panou.add(normalButon);
        lang = new JComboBox();
        lang.setBounds(10, 10, 60, 30);
        lang.addItem("Ro");
        lang.addItem("En");
        lang.addItem("Fr");
        lang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lang.getSelectedItem().toString().toLowerCase().equals("fr")) {
                    language.setLanguage("fr");
                    aux="Fr";
                    updateLanguage();
                } else if (lang.getSelectedItem().toString().toLowerCase().equals("ro")) {
                    language.setLanguage("ro");
                    aux="Ro";
                    updateLanguage();
                } else {
                    language.setLanguage("en");
                    aux="En";
                    updateLanguage();
                }
            }
        });

        panou.add(lang);
        frame.setVisible(true);
        frame.setContentPane(panou);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        normalButon.addActionListener(cStart.deschideNormal(frame));
        loginButton.addActionListener(cStart.deschideLogin(frame));
    }


    public void updateLanguage() {
        loginButton.setText(language.getLanguageText("Start.button.login"));
        normalButon.setText(language.getLanguageText("Start.button.normal"));
    }


}
