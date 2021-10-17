package logicaNegocios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import static logicaNegocios.Conexion.getConexion;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class ConsultasCurso {
  
  
  
   public boolean registar(Curso pCurso) {
    PreparedStatement ps = null;
    Connection con = getConexion();

    String sql = "INSERT INTO Curso (codigoCurso, nombreCurso, creditos, horasLectivas) VALUES (?,?,?,?)";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pCurso.getCodigo());
      ps.setString(2, pCurso.getNombre());
      ps.setInt(3, pCurso.getCreditos());
      ps.setInt(4, pCurso.getHorasLectivas());
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

  public void listarCursoEscuela(JComboBox cbxEscuela, JComboBox cbxCurso) {
    cbxCurso.removeAllItems();
    ConsultasEscuela consultasEscuela = new ConsultasEscuela();
    String codigoEscuela  = consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString());
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT * FROM Curso WHERE codigoCurso LIKE  ?";

    try {
      ps = con.prepareStatement(sql);
      String cod = "%"+codigoEscuela+"%";
      ps.setString(1, cod);
//      System.err.println(cod);
      rs = ps.executeQuery();

      while (rs.next()) {
        cbxCurso.addItem(rs.getString("codigoCurso"));
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
  
  public void listarCursos(JComboBox cbx) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT * FROM Curso";

    try {
      ps = con.prepareStatement(sql);
//      System.err.println(cod);
      rs = ps.executeQuery();

      while (rs.next()) {
        cbx.addItem(rs.getString("codigoCurso"));
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
  
  public void registrarRequisito(Curso pCurso, JComboBox requisito){
//   
//     PreparedStatement ps = null;
//    Connection con = getConexion();
//
//    String sql = "";
//
//    try {
//      ps = con.prepareStatement(sql);
//      ps.setString(1, pCurso.getCodigo());
//      ps.setString(2, pCurso.getNombre());
//      ps.setInt(3, pCurso.getCreditos());
//      ps.setInt(4, pCurso.getHorasLectivas());
//      ps.execute();
//      return true;
//
//    } catch (SQLException e) {
//      System.err.println(e);
//      return false;
//    } finally {
//      try {
//        con.close();
//      } catch (SQLException e) {
//        System.err.println(e);
//      }
//    }
  }
  
  

    
}

