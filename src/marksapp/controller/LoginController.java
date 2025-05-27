/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import marksapp.dao.UserDao;
import marksapp.model.LoginRequest;
import marksapp.model.UserData;
import marksapp.view.DashboardView;
import marksapp.view.LoginView;

/**
 *
 * @author being
 */
public class LoginController {
    private LoginView view;
    public LoginController(LoginView view){
        this.view = view;
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
    class LoginUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailTextField().getText();
            String password = String.valueOf(view.getPasswordTextField().getPassword());
            if(email.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(view, "Fill in all the fields.");
            }else{
                LoginRequest loginReq = new LoginRequest(email,password);
                UserDao userDao = new UserDao();
                UserData user = userDao.loginUser(loginReq);
                if(user == null){
                    JOptionPane.showMessageDialog(view, "Login failed");
                }else{
                    DashboardView dashbaordView = new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashbaordView,user);
                    dashboardController.open();
                    close();
                }
            }
        }
    }
}
