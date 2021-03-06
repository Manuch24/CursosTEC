package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.CursoDAO;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import logicaNegocios.Curso;
import logicaNegocios.Escuela;
import logicaNegocios.PlanEstudio;
import vista.FrmAnadirCurso;
import vista.FrmConsultarPlan;
import vista.FrmCursos;
import vista.FrmEscuela;
import vista.FrmMenu;
import vista.FrmPlanEstudio;
import vista.FrmRequisitos;
import vista.FrmModificaciones;


/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlMenu implements ActionListener{
  //Vsriables
  private FrmMenu frmMenu;
  private CtrlCurso ctrlCurso;
  private CtrlEscuela ctrlEscuela;
//  private Escuela mod;
//  private EscuelaDAO modC;
//  private FrmEscuela frm;
  private CtrlRequisitos ctrlRequisitos;
  private CtrlPlanEstudio ctrlPlanEstudio;
  private CtrlAnadirCurso ctrlAnadirCurso;
  private CtrlModificaciones ctrlModificaciones;
  
    /**
     * Conntructor vacio del menú
     */
    public CtrlMenu(){
    //Se inician los ctrl, vistas y consultas necesarias
    this.frmMenu = new FrmMenu();
    this.ctrlEscuela = new CtrlEscuela();
    //se debe inicarlizar los botones
    this.frmMenu.btnRegistrarCurso.addActionListener(this);
    this.frmMenu.btnEscuela.addActionListener(this);
    this.frmMenu.btnAsignarRequisitos.addActionListener(this);
    this.frmMenu.btnRgistrarPlanes.addActionListener(this);
    this.frmMenu.btnAnadirCursoPlan.addActionListener(this);
    this.frmMenu.btnConsultarPlanes.addActionListener(this);
    this.frmMenu.BtnModificaciones.addActionListener(this);
  }
  
    /**
     *  Método para inicializar la ventana de menu
     */
    public void iniciar(){
    frmMenu.setTitle("Menú de Planes y cursos");
    frmMenu.setVisible(true);
    frmMenu.setLocationRelativeTo(null);
  }

  @Override
       /**
     * Método donde se programan las acciones de cada uno de los botones o
     * eventos
     * 
     *  @param ActionEvent Es la accion que se aplica al boton
     */
  public void actionPerformed(ActionEvent e) {
    //Abre la vista curso por medio del ctrlCurso, este tiene médoto iniciar
    if (e.getSource() == frmMenu.btnRegistrarCurso){
      Curso curso = new Curso();
      CursoDAO cursoDAO = new CursoDAO();
      FrmCursos frmCurso = new FrmCursos();   
      this.ctrlCurso = new CtrlCurso(curso, cursoDAO, frmCurso);
      frmMenu.setVisible(false);
      ctrlCurso.iniciar();
    }
    if(e.getSource() == frmMenu.btnEscuela){
      frmMenu.setVisible(false);
      ctrlEscuela.iniciar();
    }
    
      if (e.getSource() == frmMenu.btnAsignarRequisitos) {
      Curso curso = new Curso();
      CursoDAO cursoDAO = new CursoDAO();
      FrmRequisitos frmRequisitos = new FrmRequisitos();
      this.ctrlRequisitos = new CtrlRequisitos(curso, cursoDAO, frmRequisitos);
      frmMenu.setVisible(false);
      ctrlRequisitos.iniciar();
    }
      if(e.getSource() == frmMenu.btnRgistrarPlanes){
        PlanEstudio plan = new PlanEstudio();
        PlanEstudioDAO PlanEstudioDAO = new PlanEstudioDAO();
        FrmPlanEstudio frmPlanEstudio = new FrmPlanEstudio();
        this.ctrlPlanEstudio = new CtrlPlanEstudio(plan,PlanEstudioDAO, frmPlanEstudio);
        frmMenu.setVisible(false);
        ctrlPlanEstudio.iniciar();
      }
      if (e.getSource()==frmMenu.btnAnadirCursoPlan){
      Curso curso = new Curso();
      PlanEstudio planEstudio = new PlanEstudio();
      CursoDAO cursoDAO = new CursoDAO();
      PlanEstudioDAO PlanEstudioDAO = new PlanEstudioDAO();
      FrmAnadirCurso frmAnadirCurso = new FrmAnadirCurso();
      this.ctrlAnadirCurso = new CtrlAnadirCurso(curso, planEstudio, cursoDAO, PlanEstudioDAO, frmAnadirCurso);
      frmMenu.setVisible(false);
      ctrlAnadirCurso.iniciar();
    }
     if(e.getSource()==frmMenu.btnConsultarPlanes){
       EscuelaDAO escuelaDAO = new EscuelaDAO();
       PlanEstudioDAO planEstudioDAO = new PlanEstudioDAO();
       FrmConsultarPlan frmConsultarPlan = new FrmConsultarPlan();
       CtrlConsultarPlan ctrlConsultarPlan = new CtrlConsultarPlan(escuelaDAO, planEstudioDAO, frmConsultarPlan);
      frmMenu.setVisible(false);
      ctrlConsultarPlan.iniciar();
     }
     if(e.getSource()==frmMenu.BtnModificaciones){
         Curso curso = new Curso();
         CursoDAO cursoDAO = new CursoDAO();
         PlanEstudio planEstudio = new PlanEstudio();
         PlanEstudioDAO planEstudioDAO = new PlanEstudioDAO();
         FrmModificaciones modificaciones = new FrmModificaciones();
         this.ctrlModificaciones = new CtrlModificaciones(curso,planEstudio,cursoDAO,planEstudioDAO,modificaciones);
         frmMenu.setVisible(false);
         ctrlModificaciones.iniciar();
     }

  }
  
}
