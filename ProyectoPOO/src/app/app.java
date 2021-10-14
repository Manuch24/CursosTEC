package app;

import controlador.CtrlEscuela;
import java.time.LocalDate;
import java.util.Date;
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
    
    Date date = new Date();
    
  PlanEstudio plan1 = new PlanEstudio(2050, date);
  
  plan1.registrarCursoPlan("SIE", "TI8989", 3, 9, 1);
  plan1.registrarCursoPlan("Conta 1", "TI1012", 3, 9, 2);
  
    plan1.registrarCursoPlan("SIE", "TI8989", 3, 9, 4);

  
  System.out.println(plan1.toString());


  
  }
  
  
}
