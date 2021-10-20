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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
        String numPlanBD = (rs.getString("numPlan"));
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

  public String existeCursoPlan(String codigoCurso, String numPlan) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT * FROM Bloque WHERE codigoCurso = ? AND numPlan = ?";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, codigoCurso);
      ps.setString(2, numPlan);
      rs = ps.executeQuery();
      if (rs.next()) {
        String codigoCursoBD = (rs.getString("codigoCurso"));
        return codigoCursoBD;
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
  

  public boolean ingresarCurso(String numBloque, String numPlan, String codigoCurso) {
    PreparedStatement ps = null;
    Connection con = getConexion();

    String sql = "INSERT INTO Bloque (numBloque,numPlan,codigoCurso) VALUES (?,?,?)";

    try {
      ps = con.prepareStatement(sql);

      ps.setInt(1, Integer.parseInt(numBloque));
      ps.setString(2, numPlan);
      ps.setString(3, codigoCurso);
      ps.execute();
      return true;
    } catch (SQLException e) {
      System.err.println(e);
      return false;
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }

  public void consultarPlan(String numPlan, JTable JTableBloque) {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT Bloque.codigoCurso, Curso.nombreCurso, Bloque.numBloque, Curso.horaslectivas, Curso.creditos\n" +
                        "FROM Bloque INNER JOIN Curso ON Bloque.codigoCurso = Curso.codigoCurso WHERE Bloque.numPlan = ?\n" +
                         " ORDER BY Bloque.numBloque ASC";

    try {
      //Se declara el modelo de la tabla
      DefaultTableModel modelo = new DefaultTableModel();
      JTableBloque.setModel(modelo);
//      String numPlan = cbxPlan.getSelectedItem().toString();
      ps = con.prepareStatement(sql);
      ps.setString(1, numPlan);
      rs = ps.executeQuery();
      
      ResultSetMetaData rsMd = rs.getMetaData();
      int cantColumnas = rsMd.getColumnCount();
      
      modelo.addColumn("codigoCurso");
      modelo.addColumn("nombreCurso");
      modelo.addColumn("numBloque");
      modelo.addColumn("horasLectivas");
      modelo.addColumn("creditos");

    
        while (rs.next()) {
          Object[] filas = new Object[cantColumnas];
          
          for(int i = 0; i < cantColumnas; i++){
            filas[i] = rs.getObject(i+1);
          }
          modelo.addRow(filas);
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
  
  public void consultarPlanEstudio(String numPlan, JTable jTablePlan){
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();
    
    String consulta="select Curso.codigoCurso, Curso.nombreCurso, PlanEstudio.numPlan from Curso\n" + 
            "inner join Bloque on Bloque.codigoCurso=Curso.codigoCurso\n" +
            "inner join PlanEstudio on PlanEstudio.numPlan=Bloque.numPlan where PlanEstudio.numPlan = ?";
    
    try{
        DefaultTableModel modelo = new DefaultTableModel();
        jTablePlan.setModel(modelo);
      
        ps = con.prepareStatement(consulta);
        ps.setString(1, numPlan);
      
        rs = ps.executeQuery();
      
        ResultSetMetaData rsMd = rs.getMetaData();
        int cantColumnas = rsMd.getColumnCount();
        
        modelo.addColumn("Codigo Curso");
        modelo.addColumn("Nombre Curso");
        modelo.addColumn("Numero de plan");
        
        while(rs.next()){
            Object[] filas = new Object[cantColumnas];
          
          for (int i=0;i < cantColumnas;i++){
              filas[i] = rs.getObject(i+1);
          }
          modelo.addRow(filas);
        }
        rs.close();
    }catch (SQLException e){
        System.out.println(e);
    } finally {
        try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
  
  public void borrarCursoPlan(String numplan,JTable jTablePlan){
      Connection con = getConexion();
      
      int row = jTablePlan.getSelectedRow();
      
      
      String valor = (jTablePlan.getModel().getValueAt(row, 0).toString());
      
      String query = "delete from Bloque where codigoCurso ="+"'"+ valor +"'"+ "and numPlan ="+ "'"+numplan+"'";
      
      try{
          PreparedStatement pst= con.prepareStatement(query);
           pst.executeUpdate();
           
           DefaultTableModel model = (DefaultTableModel)jTablePlan.getModel();
           model.setRowCount(0);
           
      }
      catch (SQLException ex) {
           Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public void crearPDF(){
      
  }
}
