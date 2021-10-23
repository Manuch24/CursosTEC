package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logicaNegocios.CursoDAO;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import logicaNegocios.Curso;
import vista.FrmRequisitos;


/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlRequisitos implements ActionListener{
  private EscuelaDAO escuelaDAO;
  private Curso mod;
  private FrmRequisitos frm;
  private CursoDAO cursoDAO;
  private PlanEstudioDAO planEstudioDAO;
  
    /**
     * Constructor de la clase controlador de requisitos
     * @param curso Modelo del curso
     * @param cursoDAO  EL DAO que maneja los cursos
     * @param frm La vista que maneja este controlador
     */
    public CtrlRequisitos(Curso curso, CursoDAO cursoDAO, FrmRequisitos frm){
    this.escuelaDAO = new EscuelaDAO();
    this.planEstudioDAO = new PlanEstudioDAO();
    this.mod = curso;
    this.cursoDAO =  cursoDAO;
    this.frm = frm;
    this.frm.btnVolver.addActionListener(this);
    this.frm.btnRegistrarRequisito.addActionListener(this);
    this.frm.btnRegistrarCorrequisito.addActionListener(this);
    this.frm.cbxCurso.addActionListener(this);
    this.frm.cbxEscuela.addActionListener(this);
    this.frm.cbxRequisito.addActionListener(this);
    this.frm.cbxCorrequisito.addActionListener(this);
    llenadoCbxEscuelas();
    llenadoCbxCursosEscuela();
    llenadoCbxRequisitos();
  
    
  }

  CtrlRequisitos() {
  }
  
  /***
   * Método que completa el JComboBox con las escuelas registradas 
   */
   public void llenadoCbxEscuelas(){
    escuelaDAO.listarEscuelas(this.frm.cbxEscuela);
  }
   
   /***
    * Método que llena el JComboBox que corresponde a los cursos según la escuela
    */
   public void llenadoCbxCursosEscuela(){
     cursoDAO.listarCursoEscuela(this.frm.cbxEscuela,this.frm.cbxCurso);
   }
   
   /**
    * Método que llena los JComboBox de requisitos y correquistitos
    */
   public void llenadoCbxRequisitos(){
//     cursoDAO.listarCursosRequisitos(this.frm.cbxRequisito,this.frm.cbxCurso);
//     cursoDAO.listarCursosRequisitos(this.frm.cbxCorrequisito, this.frm.cbxCurso);
     cursoDAO.listarCursos(this.frm.cbxRequisito);
     cursoDAO.listarCursos(this.frm.cbxCorrequisito);
   }
   
/***
 * Método que inicia la vista del formulario
 * 
 */
  public void iniciar(){
    frm.setTitle("Registro de Requisitos y Correquisitos");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);

  }

 @Override
  public void actionPerformed (ActionEvent e){
  //Volver al menu
    if(e.getSource()==frm.btnVolver){
      frm.setVisible(false);
      //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
      CtrlMenu ctrlMenu = new CtrlMenu();
      ctrlMenu.iniciar();
    }
    if(e.getSource()==frm.cbxCurso){
      llenadoCbxRequisitos();
    }
    
    if(e.getSource() == frm.cbxEscuela){
      llenadoCbxCursosEscuela();
    }
    
    if (e.getSource() == frm.btnRegistrarRequisito){
     if ((frm.cbxCurso.getSelectedItem().toString().equals(frm.cbxRequisito.getSelectedItem().toString()) != true)) {
       try {
         if (cursoDAO.registrarRequisito(frm.cbxCurso, frm.cbxRequisito)) {
           JOptionPane.showMessageDialog(null, "Requisito Guardado  ");
         } else {
           JOptionPane.showMessageDialog(null, "Error al guardar, no se puede guardar el mismo requisito");
         }
       } catch (SQLException ex) {
         Logger.getLogger(CtrlRequisitos.class.getName()).log(Level.SEVERE, null, ex);
       }
     } else {
       JOptionPane.showMessageDialog(null, "No se puede elegir el mismo curso como requisito");
     }
   }


    if (e.getSource() == frm.btnRegistrarCorrequisito) {
      if ((frm.cbxCurso.getSelectedItem().toString().equals(frm.cbxCorrequisito.getSelectedItem().toString()) != true)) {
        try {
          if (cursoDAO.registrarCorrequisito(frm.cbxCurso, frm.cbxCorrequisito)) {
            JOptionPane.showMessageDialog(null, "Requisito Guardado  ");
          } else {
            JOptionPane.showMessageDialog(null, "Error al guardar, no se puede guardar el mismo Corrrequisito  ");
          }
        } catch (SQLException ex) {
          Logger.getLogger(CtrlRequisitos.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        JOptionPane.showMessageDialog(null, "No se puede elegir el mismo curso como correquisito");
      }
    }
  }

}
