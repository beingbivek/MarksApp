/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.controller;

import marksapp.model.UserData;
import marksapp.view.DashboardView;

/**
 *
 * @author being
 */
public class DashboardController {
    DashboardView view;
    UserData user;
    public DashboardController(DashboardView view, UserData user){
        this.view = view;
        this.user = user;
        String name = user.getName();
        this.view.getWelcomeLabel().setText("Welcome "+name);
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    
}
