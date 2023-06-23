/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Miguel
 */
public class CAlumnos {
    
    
    int codigo;
    String nombreAlumnos;
    String apellidoAlumnos;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidoAlumnos() {
        return apellidoAlumnos;
    }

    public void setApellidoAlumnos(String apellidoAlumnos) {
        this.apellidoAlumnos = apellidoAlumnos;
    }
    
    //Variables Globales
    CConexion objetoConexion = new CConexion();
    String crear = "Insert into Alumnos (nombres,apellidos) values (?,?);";
    String leer =  "Select * from alumnos;";
    String eliminar = "DELETE FROM  Alumnos Where alumnos.id=?;";
    String actualizar = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id=?; ";
            
               
    //CRUD
    //AGREGAR
    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos){
        setNombreAlumnos(paramNombres.getText());
        setApellidoAlumnos(paramApellidos.getText());
        
        
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(crear);
         
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());       
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());

        }
        
        
    }
    
    
    //LEER
    public void MostrarAlumnos (JTable paramTablaTotalAlumnos) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        
        modelo.addColumn("id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        
        String [] datos = new String [3];
        Statement st;
        
        try {
            st=objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(leer);
             
            while(rs.next()){
                
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);
            }
            paramTablaTotalAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.toString());
            
        }
    }
    
    
    //SELECCIONAR
    public void SeleccionarAlumno(JTable paramTablaAlumnos, 
                                    JTextField paramId, JTextField paramNombres, JTextField paramApellidos ){
        
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if ( fila >= 0 ){
            
                paramId.setText((String) (paramTablaAlumnos.getValueAt(fila, 0).toString()));
                paramNombres.setText((String) (paramTablaAlumnos.getValueAt(fila, 1).toString()));
                paramApellidos.setText((String) (paramTablaAlumnos.getValueAt(fila, 2).toString()));
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en : " + e.toString());
        }
    }
    
    
    //ACTUALIZAR
    public void ActualizarDatos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidoAlumnos(paramApellidos.getText());
        
        
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(actualizar);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro actualizado");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error : " + e.toString());
        }
    }
    
    
    //ELIMINAR
    public void EliminarAlumno(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(eliminar);
            
            cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
            
        } catch (Exception e) {
            
            
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
            
        }
        
    }
    
}
