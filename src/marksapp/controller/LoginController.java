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
import marksapp.controller.mail.SMTPSMailSender;
import marksapp.dao.UserDao;
import marksapp.model.LoginRequest;
import marksapp.model.ResetPasswordRequest;
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
        this.view.forgotpassword(new ResetPassword());
        this.view.openLoginPage(new LoginUser());
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
    
    class ResetPassword implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String email = JOptionPane.showInputDialog(view,"Enter your email");
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(view, "Email cannot be empty");
            }else{
                UserDao userDao = new UserDao();
                boolean emailExists = userDao.checkEmail(email);
                if(!emailExists){
                    JOptionPane.showMessageDialog(view,"Email doesnot exist");
                }else{
                    String otp = "987586";
                    boolean mailSent = SMTPSMailSender.sendMail(email,"Reset Password Verfication","The OTP to reset your password is "+otp);
                    if(!mailSent){
                        JOptionPane.showMessageDialog(view, "Failed to send OTP. Try Again later!");
                    }else{
                        String otpReceived = JOptionPane.showInputDialog(email,"Enter youe otp code");
                        if(!otp.equals(otpReceived)){
                            JOptionPane.showMessageDialog(view, "OTP did not Match");
                        }else{
                            String password = JOptionPane.showInputDialog(view,"Enter your new password");
                            if(password.trim().isEmpty()){
                                JOptionPane.showMessageDialog(view, "Password is Empty");
                            }else{
                                ResetPasswordRequest resetPassword = new ResetPasswordRequest(email,password);
                                boolean updateResult = userDao.resetPassword(resetPassword);
                                if(!updateResult){
                                    JOptionPane.showMessageDialog(view, "Failed to reset password");
                                }else{
                                    JOptionPane.showMessageDialog(view,"Password has been changed");
                                }
                            }
                        }
                    }
                }
            }
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
