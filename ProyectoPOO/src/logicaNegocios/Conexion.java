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
  
    /**
     * Método en el cual se gurada la información para la conexión  con la base de datos 
     * @return con si la conexion se realzón de manera exitosa
     */
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
