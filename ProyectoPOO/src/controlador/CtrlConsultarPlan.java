package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import vista.FrmConsultarPlan;
import vista.FrmEnvioCorreo;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class CtrlConsultarPlan implements ActionListener{
  private PlanEstudioDAO planEstudioDAO;
  private EscuelaDAO escuelaDAO;
  private FrmConsultarPlan frm;
  private FrmEnvioCorreo envioCorreo;
  
  
  public CtrlConsultarPlan(EscuelaDAO escuelaDAO, PlanEstudioDAO planEstudioDAO, FrmConsultarPlan frm){
    this.escuelaDAO = escuelaDAO;
    this.planEstudioDAO = planEstudioDAO;
    this.frm = frm;
    this.frm.btnVolver.addActionListener(this);
    this.frm.btnPDF.addActionListener(this);
    this.frm.cbxEscuela.addActionListener(this);
    this.frm.cbxPlan.addActionListener(this);
    this.frm.btnCargar.addActionListener(this);
  }
  
  public void iniciar() {
    frm.setTitle("Registro de Planes");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
    llenadoCbxEscuelas();
    llenadoCbxPlan();
//    llenadoTabla();
  }
  
  public void llenadoCbxEscuelas() {
    escuelaDAO.listarEscuelas(frm.cbxEscuela);
  }

  public void llenadoCbxPlan() {
    planEstudioDAO.listarPlanes(frm.cbxPlan, frm.cbxEscuela);
  }
  
  public void llenadoTabla(){
    planEstudioDAO.consultarPlan(frm.cbxPlan.getSelectedItem().toString(),frm.jTableBloque,frm.LabelFecha);
    planEstudioDAO.contadores(frm.cbxPlan.getSelectedItem().toString(),frm.LabelCursos,frm.LabelCreditos);
  }
  public void crearPDF() throws SQLException{
    planEstudioDAO.crearPDF();
  }
  
  public void abrirEnviarEmail(){
      
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //Volver al menu
    if (e.getSource() == frm.btnVolver) {
      frm.setVisible(false);
      //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
      CtrlMenu ctrlMenu = new CtrlMenu();
      ctrlMenu.iniciar();
    }
    if(e.getSource() == frm.cbxEscuela){
      llenadoCbxPlan();
//      llenadoTabla();
    }
    if(e.getSource() == frm.btnCargar){
      llenadoTabla();
    }
    
    if(e.getSource()== frm.btnPDF){
        try {
            FrmEnvioCorreo frmenviar = new FrmEnvioCorreo();
            PlanEstudioDAO planEstudioDAO = new PlanEstudioDAO();
            FrmConsultarPlan frm = new FrmConsultarPlan();
            CtrlEnvioCorreo ctrlenvioEmail = new CtrlEnvioCorreo(frmenviar,planEstudioDAO,frm); 
            ctrlenvioEmail.iniciar();                          
            crearPDF();
            //envioCorreo.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(CtrlConsultarPlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  }
}
