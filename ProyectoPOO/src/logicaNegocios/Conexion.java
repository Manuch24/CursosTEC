/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves 
 */
public class Conexion {
  
  public static Connection getConexion(){
    String conexionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Progra1POO";
       try{  
         Connection con =  DriverManager.getConnection(conexionUrl,"sa","12345");
            return con;
        }catch(SQLException ex){
          System.out.println(ex.toString());
            return null;
        }  
    }
  
}
