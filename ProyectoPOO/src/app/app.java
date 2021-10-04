package app;

import java.time.LocalDate;
import logicaNegocios.Bloque;
import logicaNegocios.Curso;
import logicaNegocios.PlanEstudio;
import logicaNegocios.Escuela;


/**
 *
 * @author XT
 */
public class app {
  
  public static void main(String[] args){ 
  LocalDate fecha = LocalDate.of(2026, 12, 31);
  PlanEstudio plan1 = new PlanEstudio(2050, fecha );
  
  plan1.registrarCursoPlan("SIE", "TI8989", 3, 9, 1);
  
  System.out.println(plan1.toString());
  
  }
  
  
}
