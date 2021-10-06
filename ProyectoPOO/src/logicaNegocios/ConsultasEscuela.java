/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bryan Berrocal
 * @author MAnuel Chaves
 * 
 */
public class ConsultasEscuela extends Conexion {
  
  public boolean registar(Escuela pEscuela){
  PreparedStatement ps = null;
  Connection con = getConexion();
  
  String sql = "INSERT INTO Escuelas (codigo, nombre) VALUES (?,?)";
  
  try{
    ps = con.prepareStatement(sql);
    ps.setString(1, pEscuela.getCodigo());
    ps.setString(2, pEscuela.getNombre());
    return true;
    
  }catch(SQLException e){
    System.err.println(e);
    return false;
  }finally{
    try{
      con.close();
    }catch (SQLException e){
      System.err.println(e);
    }
  } 
  }
  
  public boolean buscar(Escuela pEscuela){
   PreparedStatement ps = null;
    ResultSet rs = null;
   Connection con = getConexion();
  
   String sql = "Select nombre FROM Escuelas WHERE codigo=? ";
  
   try{
     ps = con.prepareStatement(sql);
     ps.setString(1, pEscuela.getCodigo());
     rs = ps.executeQuery();
     
     if(rs.next()){
       
       pEscuela.setCodigo(rs.getString("codigo"));
       pEscuela.setNombre(rs.getString("nombre"));
     }
     return true;
   }catch(SQLException e){
     System.err.println(e);
     return false;
   }finally{
     try{
       con.close();
     }catch (SQLException e){
       System.err.println(e);
     }
   } 
  }
}
