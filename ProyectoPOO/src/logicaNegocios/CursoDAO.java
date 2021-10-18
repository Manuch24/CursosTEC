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
public class CursoDAO {
  
  
  
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
   /***
    * 
    * @param cbxEscuela
    * @param cbxCurso 
    */
  public void listarCursoEscuela(JComboBox cbxEscuela, JComboBox cbxCurso) {
    cbxCurso.removeAllItems();
    EscuelaDAO consultasEscuela = new EscuelaDAO();
    //Se usa el método buscar codigo de la escuela según el nombre
    //Este retorna el codigo para lograr buscar el curso en la base de datos
    String codigoEscuela  = consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString());
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT * FROM Curso WHERE codigoCurso LIKE  ?";

    try {
      ps = con.prepareStatement(sql);
      String cod = "%"+codigoEscuela+"%";
      ps.setString(1, cod);
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
  /***
   * 
   * @param cbx 
   */
  public void listarCursos(JComboBox cbx) {
    cbx.removeAllItems();
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
  
  /***
   * 
   * @param cbxCurso
   * @param cbxRequisito
   * @return
   * @throws SQLException 
   */
  public boolean registrarRequisito(JComboBox cbxCurso, JComboBox cbxRequisito) throws SQLException{
    PreparedStatement ps = null;
    Connection con = getConexion();

    String sql = "INSERT INTO cursoRequisito (codigoCurso,idRequisito) VALUES (?,?)";
    
    try{
      ps = con.prepareStatement(sql);
      ps.setString(1, cbxCurso.getSelectedItem().toString());
      ps.setString(2,cbxRequisito.getSelectedItem().toString());
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
  
    public boolean registrarCorrequisito(JComboBox cbxCurso, JComboBox cbxCorrequisito) throws SQLException{
    PreparedStatement ps = null;
    Connection con = getConexion();

    String sql = "INSERT INTO cursoCorrequisito (codigoCurso,idCorrequisito) VALUES (?,?)";
    
    try{
      ps = con.prepareStatement(sql);
      ps.setString(1, cbxCurso.getSelectedItem().toString());
      ps.setString(2,cbxCorrequisito.getSelectedItem().toString());
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
  
  
public void listarCursosRequisitos(JComboBox cbxRequisito, JComboBox cbxCurso ) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT codigoCurso FROM Curso EXCEPT SELECT codigoCurso FROM Curso WHERE codigoCurso =?";

    try {
      String cod = cbxCurso.getSelectedItem().toString();
      ps = con.prepareStatement(sql);
      ps.setString(1, cod);
      rs = ps.executeQuery();

      while (rs.next()) {
        cbxRequisito.addItem(rs.getString("codigoCurso"));
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
  
    
}

