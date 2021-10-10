package logicaNegocios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static logicaNegocios.Conexion.getConexion;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class ConsultasCurso {
  
    public boolean registar(Curso pCurso){
  PreparedStatement ps = null;
  Connection con = getConexion();
  
  String sql = "INSERT INTO Curso (codigoCurso, nombreCurso, creditos, horasLectivas) VALUES (?,?,?,?)";
  
  try{
    ps = con.prepareStatement(sql);
    ps.setString(1, pCurso.getCodigo());
    ps.setString(2, pCurso.getNombre());
    ps.setInt(3, pCurso.getCreditos());
    ps.setInt(4, pCurso.getHorasLectivas());
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
  
}
