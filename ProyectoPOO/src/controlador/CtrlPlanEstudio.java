/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import logicaNegocios.PlanEstudio;
import vista.FrmPlanEstudio;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class CtrlPlanEstudio implements ActionListener{
  private PlanEstudio planEstudio;
  private PlanEstudioDAO planEstudioDAO;
  private FrmPlanEstudio frm;
  private CtrlMenu CtrlMenu;
  private EscuelaDAO escuelaDAO;
  
  public CtrlPlanEstudio(PlanEstudio planEstudio, PlanEstudioDAO planEstudioDAO, FrmPlanEstudio frm){
    this.planEstudio = planEstudio;
    this.planEstudioDAO = planEstudioDAO;
    this.frm = frm;
    this.CtrlMenu = new CtrlMenu();
    this.escuelaDAO = new EscuelaDAO();
    this.frm.btnVolver.addActionListener(this);
    this.frm.cbxEscuela.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
    llenarCbxEscuela();
  }
  /***
   * Método que inicia el formulario
   */
  public void iniciar() {
    frm.setTitle("Registro de Planes");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
  }
  /***
   * Método que completa el JComboBox de escuelas
   */
  public void llenarCbxEscuela(){
    escuelaDAO.listarEscuelas(this.frm.cbxEscuela);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == frm.btnRegistrar) {
      if (frm.txtCodigoPlan.getText() == "" || frm.jDateChooser1.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Llene  todos los campos");
      } else {
        if (planEstudioDAO.numeroPlan(frm.txtCodigoPlan.getText()).equals(frm.txtCodigoPlan.getText())) {
          JOptionPane.showMessageDialog(null, "Error: El número de plan escogido ya lo utiliza otra escuela");
        } else {
          planEstudio.setNumPlan(Integer.parseInt(frm.txtCodigoPlan.getText()));
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String format = simpleDateFormat.format(frm.jDateChooser1.getDate());
          
          if (planEstudioDAO.registrar(planEstudio, frm.cbxEscuela, format)) {
            JOptionPane.showMessageDialog(null, "Registro de escuela guardado");
          } else {
            JOptionPane.showMessageDialog(null, "Error este número de plan ya lo usa otra escuela  ");
          }
        }
      }
    }
    if(e.getSource()==frm.btnVolver){
      frm.setVisible(false);
      CtrlMenu.iniciar();
    }
  }
  
  
}
