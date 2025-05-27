/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package marksapp;

import marksapp.controller.RegisterController;
//import marksapp.view.LoginView;
import marksapp.view.RegistrationView;

/**
 *
 * @author being
 */
public class MarksApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        LoginView loginView = new LoginView();
//        loginView.setVisible(true);
        RegistrationView registrationView = new RegistrationView();
        RegisterController registerController = new RegisterController(registrationView);
        registerController.open();
    }
    
}
