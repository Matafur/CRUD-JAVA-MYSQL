/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class CConexion {
    
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "Mapfre2020";
    String dataBase = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+dataBase;
    
    public Connection estableceConexion (){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
           // JOptionPane.showMessageDialog(null, "Estas conectado a la BD");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error conexion" + e.toString());
        }
        return conectar;
    }
}
