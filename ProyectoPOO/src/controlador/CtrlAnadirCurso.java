/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.CursoDAO;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import logicaNegocios.Curso;
import logicaNegocios.PlanEstudio;
import vista.FrmAnadirCurso;

/**
 *
 * @author XT
 */
public class CtrlAnadirCurso implements ActionListener{
  private FrmAnadirCurso frm;
  private CursoDAO cursoDAO;
  private PlanEstudioDAO planEstudioDAO;
  private Curso curso;
  private PlanEstudio planEstudio;
  private EscuelaDAO escuelaDAO;
  
  public CtrlAnadirCurso(){
    
  }
  
  public CtrlAnadirCurso(Curso curso,PlanEstudio planEstudio, CursoDAO cursoDAO, PlanEstudioDAO planEstudioDAO,FrmAnadirCurso frm){
    this.cursoDAO = cursoDAO;
    this.planEstudioDAO = planEstudioDAO;
    this.planEstudio = planEstudio;
    this.curso = curso;
    this.frm = frm;
    this.escuelaDAO = new EscuelaDAO();
    
    this.frm.btnVolver.addActionListener(this);
    this.frm.cbxEscuela.addActionListener(this);
    this.frm.cbxPlan.addActionListener(this);
  }
  
    public void iniciar() {
    frm.setTitle("Registro de Planes");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
    llenadoCbxEscuelas();
    llenadoCbxPlan();
    llenadoCbxCurso();
  }
  
  public void llenadoCbxEscuelas() {
    escuelaDAO.listarEscuelas(frm.cbxEscuela);
  }

  public void llenadoCbxPlan() {
    planEstudioDAO.listarPlanes(frm.cbxPlan, frm.cbxEscuela);
  }
  
  public void llenadoCbxCurso() {
    cursoDAO.listarCursos(frm.cbxCurso);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      //Volver al menu
    if(e.getSource()==frm.btnVolver){
      frm.setVisible(false);
      //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
      CtrlMenu ctrlMenu = new CtrlMenu();
      ctrlMenu.iniciar();
    }
    if (e.getSource() == frm.cbxEscuela) {
      llenadoCbxPlan();
    }
  }
    
    
  
}
