package app;

import com.sun.tools.javac.jvm.CRTable;
import controlador.CtrlEscuela;
import java.time.LocalDate;
import logicaNegocios.Bloque;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.Curso;
import logicaNegocios.PlanEstudio;
import logicaNegocios.Escuela;
import vista.FrmEscuela;


/**
 *
 * @author XT
 */
public class app {
  
  public static void main(String[] args){ 
    Escuela mod = new Escuela();
    ConsultasEscuela modC = new ConsultasEscuela();
    FrmEscuela frm = new FrmEscuela();
    
    CtrlEscuela ctrl = new CtrlEscuela(mod, modC, frm);
    ctrl.iniciar();
    frm.setVisible(true);
    
//  LocalDate fecha = LocalDate.of(2026, 12, 31);
//  PlanEstudio plan1 = new PlanEstudio(2050, fecha );
//  
//  plan1.registrarCursoPlan("SIE", "TI8989", 3, 9, 1);
//  
//  System.out.println(plan1.toString());


  
  }
  
  
}
