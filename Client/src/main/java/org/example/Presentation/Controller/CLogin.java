package org.example.Presentation.Controller;


import org.example.Presentation.View.VAdmin;
import org.example.Presentation.View.VEmployee;
import org.example.Presentation.View.Login;
import org.example.Service.IUserService;
import org.example.Service.Implementation.UserServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLogin {

   public ActionListener login(JTextField username, JTextField parola, JFrame frame)
   {
       ActionListener e=new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();

               IUserService utilizatorService = new UserServiceImpl();
               String nume = username.getText();
               String pass = parola.getText();
               if(nume.isEmpty()){
                   JOptionPane.showMessageDialog(null, "Complete email field", "Error", JOptionPane.ERROR_MESSAGE);

                   return;
               }
               else if(pass.isEmpty()){
                   JOptionPane.showMessageDialog(null, "Complete pass field", "Error", JOptionPane.ERROR_MESSAGE);

                   return;
               }
               else{
                   Integer utilizator = utilizatorService.login(nume, pass);
                   System.out.println(utilizator);
                   if (utilizator == 1||utilizator==2) {
                       JOptionPane.showMessageDialog(null, "Succesfully login", "Success", JOptionPane.INFORMATION_MESSAGE);
                       if(utilizator==1){
                           VAdmin admin=new VAdmin();
                       }
                       if(utilizator==2){
                           VEmployee angajat=new VEmployee();
                       }
                   }
                   else {
                       Login login=new Login();
                       JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
               }


           }
       };
       return e;
   }

}
