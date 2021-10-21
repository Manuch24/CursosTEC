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
public class EscuelaDAO extends Conexion {
  
  /***
   *  Metodo para registrar la escuela
   * @param pEscuela Escuela a ingresar en la base de datos 
   * @return true si se logra el registro, false si hubo un error a insertar
   */
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
  
  /***
   * 
   * @param pEscuela Objeto a retornar en la búsqueda
   * @return true si encuentra el registro, false si no se encuentra
   */
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
  /***
   * Método que ayuda a listar el nombre de las escuelas
   * @param cbx ComboBox donde se llenará con el nombre de las escuelas
   */
   public void listarEscuelas(JComboBox cbx){
   PreparedStatement ps = null;
    ResultSet rs = null;
   Connection con = getConexion();
  
   String sql = "SELECT * FROM Escuela";
  
   try{
     ps = con.prepareStatement(sql);
     rs = ps.executeQuery();
     
     while(rs.next()){
       cbx.addItem(rs.getString("nombre"));
     }
     rs.close();
   }catch(SQLException e){
     System.err.println(e);
   }finally{
     try{
       con.close();
     }catch (SQLException e){
       System.err.println(e);
     }
   } 
  }
   /***
    * Método que busca el código de una escuela según el nombre
    * @param pNombreEscuela Nombre de la escuela
    * @return Codigo correspondiente a la escuela
    */
  public String buscarCodigo(String pNombreEscuela) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT codigoEscuela FROM Escuela WHERE nombre=?";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pNombreEscuela);
      rs = ps.executeQuery();

      if (rs.next()) {
        pNombreEscuela =(rs.getString("codigoEscuela"));
        return pNombreEscuela;
      }
      return "Error";
    } catch (SQLException e) {
      System.err.println(e);
      return "Error";
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
}
