package org.example.Presentation.View;

import org.example.Presentation.Controller.CLogin;

import javax.swing.*;
import java.awt.*;

public class Login {


    private JFrame frame;
    private JTextField username;
    private JTextField pass;
    private JButton loginButton;

    //private Language language;
    public Login() {

        //language=langTransmis;
        this.frame = new JFrame("Login");
        JPanel panou = new JPanel();
        panou.setLayout(null);
        panou.setBackground(new Color(126, 217, 252));

        JLabel userL = new JLabel("Username");
        userL.setBounds(40, 60, 200, 20);

        this.username = new JTextField();
        username.setBounds(100, 60, 200, 20);

        JLabel passL = new JLabel("Password");
        passL.setBounds(40, 100, 200, 20);

        this.pass = new JTextField();
        pass.setBounds(100, 100, 200, 20);


        this.loginButton = new JButton("Conectare");
        loginButton.setBounds(120, 130, 120, 20);
        CLogin cLogin = new CLogin();
        loginButton.addActionListener(cLogin.login(username, pass, frame));


        panou.add(loginButton);
        panou.add(passL);
        panou.add(userL);
        panou.add(username);
        panou.add(pass);



        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setContentPane(panou);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }





}

