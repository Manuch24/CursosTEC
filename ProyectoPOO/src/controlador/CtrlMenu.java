package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.ConsultasCurso;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.Curso;
import logicaNegocios.Escuela;
import vista.FrmCursos;
import vista.FrmEscuela;
import vista.FrmMenu;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlMenu implements ActionListener{
  private FrmMenu frmMenu;
  private CtrlCurso modCursoC;
  private CtrlEscuela modEscuelaC;
  private Escuela mod;
  private ConsultasEscuela modC;
  private FrmEscuela frm;
  
  public CtrlMenu(){
    //Se inician los ctrl, vistas y consultas necesarias
    this.frmMenu = new FrmMenu();
//    this.modCursoC = new CtrlCurso();
    this.modEscuelaC = new CtrlEscuela();
    //se debe inicarlizar los botones
    this.frmMenu.btnRegistrarCurso.addActionListener(this);
    this.frmMenu.btnEscuela.addActionListener(this);
  }
  
  public void iniciar(){
    frmMenu.setTitle("Menú de Planes y cursos");
    frmMenu.setVisible(true);
    frmMenu.setLocationRelativeTo(null);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    //Abre la vista curso por medio del ctrlCurso, este tiene médoto iniciar
    if (e.getSource() == frmMenu.btnRegistrarCurso){
      Curso curso = new Curso();
      ConsultasCurso consultasCurso = new ConsultasCurso();
      FrmCursos frmCurso = new FrmCursos();   
      this.modCursoC = new CtrlCurso(curso, consultasCurso, frmCurso);
      frmMenu.setVisible(false);
      modCursoC.iniciar();
    }
    if(e.getSource() == frmMenu.btnEscuela){
      frmMenu.setVisible(false);
      modEscuelaC.iniciar();
    }
    
  }
  
}
