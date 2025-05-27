/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marksapp.dao;

import java.sql.*;
import marksapp.database.MySqlConnection;
import marksapp.model.LoginRequest;
import marksapp.model.UserData;

/**
 *
 * @author being
 */
public class UserDao {
    MySqlConnection mySql = new MySqlConnection();
    public boolean register(UserData user){
        String query = "INSERT INTO users(fname,email,fpassword) values(?,?,?)";
        Connection conn = mySql.openConnection();
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,user.getName());
            stmnt.setString(2,user.getEmail());
            stmnt.setString(3,user.getPassword());
            int result = stmnt.executeUpdate();
            boolean value=result>0;    
            return value;
        }catch(SQLException e){
            return false;
        }finally{
            mySql.closeConnection(conn);
        }
    }
    
    public UserData loginUser(LoginRequest loginData){
        Connection conn = mySql.openConnection();
        String sql = "SELECT * FROM users where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginData.getEmail());
            pstmt.setString(2, loginData.getPassword());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                UserData user  = new UserData(
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password")             
                );
                user.setId(result.getString("id"));
                
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            mySql.closeConnection(conn);
        }
        return null;
    }
    
    public boolean checkEmail(String email){
        Connection conn = mySql.openConnection();
        String query = "SELECT * FROM users where email=?";
        try{
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1,email);
            ResultSet result = stmnt.executeQuery();
            return result.next();
        }catch(Exception e){
            return false;
        }finally{
            mySql.closeConnection(conn);
        }
    }
    
}
 