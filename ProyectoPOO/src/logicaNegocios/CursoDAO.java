package logicaNegocios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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

public void consultarRequisitos(String codCurso,JTable JTableElmRequisitos){
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();
    
//    String sql = "select Curso.nombreCurso,cursoRequisito.idRequisito "
//            + "from Curso inner join cursoCorrequisito on cursoCorrequisito.codigoCurso=Curso.codigoCurso"
//            + " inner join cursoRequisito on cursoRequisito.codigoCurso=Curso.codigoCurso where Curso.codigoCurso = ?";

  String sql = "select cursoRequisito.codigoCurso, cursoRequisito.idRequisito FROM cursoRequisito INNER JOIN\n" +
"Curso ON Curso.codigoCurso = cursoRequisito.codigoCurso WHERE Curso.codigoCurso = ?";
    try{
      DefaultTableModel modelo = new DefaultTableModel();
      JTableElmRequisitos.setModel(modelo);
      
      ps = con.prepareStatement(sql);
      ps.setString(1, codCurso);
      
      rs = ps.executeQuery();
      
      ResultSetMetaData rsMd = rs.getMetaData();
      int cantColumnas = rsMd.getColumnCount();
      
      modelo.addColumn("Curso buscado");
      modelo.addColumn("Codigo requisitos");
      
      while (rs.next()){
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

public void BorrarRequisitos(JTable JTableElmRequisitos){
    Connection con = getConexion();
    
    int row = JTableElmRequisitos.getSelectedRow();
    
    
    String valor = (JTableElmRequisitos.getModel().getValueAt(row, 1).toString());
    String query = "delete from cursoRequisito where idRequisito= "+"'"+valor+"'";

       try {
           PreparedStatement pst= con.prepareStatement(query);
           pst.executeUpdate();
           
           DefaultTableModel model = (DefaultTableModel)JTableElmRequisitos.getModel();
           model.setRowCount(0);
           
           
           
       } catch (SQLException ex) {
           Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
} 
    
}

