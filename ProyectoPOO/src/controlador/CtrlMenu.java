package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.ConsultasCurso;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.ConsultasPlan;
import logicaNegocios.Curso;
import logicaNegocios.Escuela;
import logicaNegocios.PlanEstudio;
import vista.FrmAnadirCurso;
import vista.FrmCursos;
import vista.FrmEscuela;
import vista.FrmMenu;
import vista.FrmPlanEstudio;
import vista.FrmRequisitos;

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
  private CtrlRequisitos ctrlRequisitos;
  private CtrlPlanEstudio ctrlPlanEstudio;
  private CtrlAnadirCurso ctrlAnadirCurso;
  
  public CtrlMenu(){
    //Se inician los ctrl, vistas y consultas necesarias
    this.frmMenu = new FrmMenu();
    this.modEscuelaC = new CtrlEscuela();
    //se debe inicarlizar los botones
    this.frmMenu.btnRegistrarCurso.addActionListener(this);
    this.frmMenu.btnEscuela.addActionListener(this);
    this.frmMenu.btnAsignarRequisitos.addActionListener(this);
    this.frmMenu.btnRgistrarPlanes.addActionListener(this);
    this.frmMenu.btnAnadirCursoPlan.addActionListener(this);

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
    
      if (e.getSource() == frmMenu.btnAsignarRequisitos) {
      Curso curso = new Curso();
      ConsultasCurso consultasCurso = new ConsultasCurso();
      FrmRequisitos frmRequisitos = new FrmRequisitos();
      this.ctrlRequisitos = new CtrlRequisitos(curso, consultasCurso, frmRequisitos);
      frmMenu.setVisible(false);
      ctrlRequisitos.iniciar();
    }
      if(e.getSource() == frmMenu.btnRgistrarPlanes){
        PlanEstudio plan = new PlanEstudio();
        ConsultasPlan consultasPlan = new ConsultasPlan();
        FrmPlanEstudio frmPlanEstudio = new FrmPlanEstudio();
        this.ctrlPlanEstudio = new CtrlPlanEstudio(plan,consultasPlan, frmPlanEstudio);
        frmMenu.setVisible(false);
        ctrlPlanEstudio.iniciar();
      }
      if (e.getSource()==frmMenu.btnAnadirCursoPlan){
        Curso curso = new Curso();
                PlanEstudio plan = new PlanEstudio();

        ConsultasCurso consultasCurso = new ConsultasCurso();
        ConsultasPlan consultasPlan = new ConsultasPlan();
        FrmAnadirCurso frmAnadirCurso = new FrmAnadirCurso();
        this.ctrlAnadirCurso = new CtrlAnadirCurso(curso, plan,consultasCurso,consultasPlan, frmAnadirCurso);
        frmMenu.setVisible(false);
        ctrlAnadirCurso.iniciar();
      }
    
  }
  
}
