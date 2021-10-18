/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import static logicaNegocios.Conexion.getConexion;

/**
 *
 * @author XT
 */
public class PlanEstudioDAO extends Conexion {

  public boolean registrar(PlanEstudio pPlanEstudio, JComboBox cbxEscuela, String date){
  PreparedStatement ps = null;
  Connection con = getConexion();

  String sql = "{call insertarPlan (?,?,?)}";

  
    try{
    ps = con.prepareStatement(sql);
    EscuelaDAO consultasEscuela = new EscuelaDAO();
    java.sql.Date date1 = java.sql.Date.valueOf(date);
    
    ps.setString(1, consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString()));
    ps.setString(2, String.valueOf(pPlanEstudio.getNumPlan()));
    ps.setDate(3, date1);
    ps.execute();
    return true;
  }
  catch(SQLException e
    ){
    System.err.println(e);
    return false;
  }
    finally{
    try {
      con.close();
    } catch (SQLException e) {
      System.err.println(e);
    }
  } 
 }
    
  public void listarPlanes(JComboBox cbxPlanes, JComboBox cbxEscuela ) {
    cbxPlanes.removeAllItems();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();
    
    //Se llama a consultasEscuela
    EscuelaDAO consultasEscuela = new EscuelaDAO();

    String sql = "SELECT numPlan FROM EscuelaPlan WHERE codigoEscuela =?";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString()));
      rs = ps.executeQuery();

      while (rs.next()) {
        cbxPlanes.addItem(rs.getString("numPlan"));
//       pEscuela.setNombre(rs.getString("nombre"))
      }
      rs.close();
    } catch (SQLException e) {
      System.err.println(e);
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
  
  public String numeroPlan(String numPlan) {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT numPlan FROM PlanEstudio WHERE numPlan = ?";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, numPlan);
      rs = ps.executeQuery();
      if (rs.next()) {
        String numPlanBD =(rs.getString("numPlan"));
        return numPlanBD;
      }
      rs.close();
      return "No existe";
    } catch (SQLException e) {
      System.err.println(e);
      return "No existe";
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
  
//  public boolean existeCursoPlan(String codigoCurso){
//    PreparedStatement ps = null;
//    ResultSet rs = null;
//    Connection con = getConexion();
//    
//    String sql = "SELECT * FROM BloqueCurso WHERE codigoCurso = ?";
//    if
//    return true;
//  }
  
  public boolean existeBloque(String numBloque){
    return true;
  }
  public boolean ingresarCurso(String numeroPlan, String codigoCurso, String numBloque){
    return true;
  }

}
