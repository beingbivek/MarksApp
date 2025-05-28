/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import marksapp.dao.UserDao;
import marksapp.model.UserData;
import marksapp.view.LoginView;
import marksapp.view.RegistrationView;

/**
 *
 * @author being
 */
public class RegisterController {
    // object for view you want to control
    private RegistrationView registerView = new RegistrationView();
    // pass the view to Constructor
    public RegisterController(RegistrationView registerView){
        // initialize the view
        this.registerView = registerView;
        this.registerView.registerUser(new RegisterUser());
    }
    public void open(){
        this.registerView.setVisible(true);
    }
    public void close(){
        this.registerView.dispose();
    }
    
    private void openLoginPage(){
        LoginView view = new LoginView();
        LoginController loginController = new LoginController(view);
        loginController.open();
    }
    
    class RegisterUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = registerView.getNameTextField().getText();
            String email =registerView.getEmailTextField().getText();
            String password = String.valueOf(registerView.getPasswordTextField().getPassword());
            String confirmPassword = String.valueOf(registerView.getConfirmPasswordTextField().getPassword());
            if (name.isEmpty()|| email.isEmpty()|| password.isEmpty()|| confirmPassword.isEmpty()){
                JOptionPane.showMessageDialog(registerView, "Fill in all the fields");
            }else if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(registerView,"Passwords do not match ");
            }else{
                UserData user = new UserData(name,email,password);
                UserDao userDao = new UserDao();
                boolean result = userDao.register(user);
                if (result){
                    JOptionPane.showMessageDialog(registerView, "Registered successfully");
                    openLoginPage();
                    close();
                }else{
                    JOptionPane.showMessageDialog(registerView, "Register failed");
                }
            }
        }
    }
    
    class LoginPageOpen implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            openLoginPage();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}