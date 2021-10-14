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
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.ConsultasPlan;
import logicaNegocios.PlanEstudio;
import vista.FrmPlanEstudio;

/**
 *
 * @author XT
 */
public class CtrlPlanEstudio implements ActionListener{
  private PlanEstudio mod;
  private ConsultasPlan modC;
  private FrmPlanEstudio frm;
  private CtrlMenu CtrlMenu;
  private ConsultasEscuela consultasEscuela;
  
  public CtrlPlanEstudio(PlanEstudio mod, ConsultasPlan modC, FrmPlanEstudio frm){
    this.mod = mod;
    this.modC = modC;
    this.frm = frm;
    this.CtrlMenu = new CtrlMenu();
    this.consultasEscuela = new ConsultasEscuela();
    this.frm.btnVolver.addActionListener(this);
    this.frm.cbxEscuela.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
    llenarCbxEscuela();
  }
  
  public void iniciar() {
    frm.setTitle("Registro de Planes");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
  }
  
  public void llenarCbxEscuela(){
    consultasEscuela.listarEscuelas(this.frm.cbxEscuela);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource()==frm.btnRegistrar){
      mod.setNumPlan(Integer.parseInt(frm.txtCodigoPlan.getText()));
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String format = simpleDateFormat.format(frm.jDateChooser1.getDate());
      
      //mod.setVigencia(simpleDateFormat.format(frm.jDateChooser1.getDate()));
      if (modC.registrar(mod, frm.cbxEscuela, format)) {
        JOptionPane.showMessageDialog(null, "Registro de escuela guardado");
        
      }else{
        JOptionPane.showMessageDialog(null, "ERROR al guardar  ");
    }
    }
  
    if(e.getSource()==frm.btnVolver){
      frm.setVisible(false);
      CtrlMenu.iniciar();
    }
  }
  
  
}
