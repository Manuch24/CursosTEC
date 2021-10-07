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
import javax.swing.JComboBox;

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
  
  String sql = "INSERT INTO Escuela (codigoEscuela, nombre) VALUES (?,?)";
  
  try{
    ps = con.prepareStatement(sql);
    ps.setString(1, pEscuela.getCodigo());
    ps.setString(2, pEscuela.getNombre());
    ps.execute();
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
  
   String sql = "SELECT * FROM Escuela WHERE codigoEscuela=?";
  
   try{
     ps = con.prepareStatement(sql);
     ps.setString(1, pEscuela.getCodigo());
     rs = ps.executeQuery();
     
     if(rs.next()){
       
       pEscuela.setCodigo(rs.getString("codigoEscuela"));
       pEscuela.setNombre(rs.getString("nombre"));

       return true;
     }
     return false;
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
  
   public boolean listarEscuelas(Escuela pEscuela, JComboBox comBox){
   PreparedStatement ps = null;
    ResultSet rs = null;
   Connection con = getConexion();
  
   String sql = "SELECT * FROM Escuela WHERE codigoEscuela=?";
  
   try{
     ps = con.prepareStatement(sql);
     ps.setString(1, pEscuela.getCodigo());
     rs = ps.executeQuery();
     
     if(rs.next()){
       
       pEscuela.setCodigo(rs.getString("codigoEscuela"));
       pEscuela.setNombre(rs.getString("nombre"));

       return true;
     }
     return false;
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
