package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.Escuela;
import vista.FrmEscuela;
import vista.FrmMenu;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlEscuela implements ActionListener {
  private Escuela mod;
  private ConsultasEscuela modC;
  private FrmEscuela frm;
  private CtrlMenu modMenuC;

  public CtrlEscuela(Escuela mod, ConsultasEscuela modC, FrmEscuela frm) {
    this.mod = mod;
    this.modC = modC;
    this.frm = frm;
    this.frm.btnBuscar.addActionListener(this);
    this.frm.btnMenu.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
    this.modMenuC = new CtrlMenu();
  }

  CtrlEscuela() {
    this.mod = new Escuela();
    this.frm = new FrmEscuela();
    this.modC = new ConsultasEscuela();
    this.frm.btnBuscar.addActionListener(this);
    this.frm.btnMenu.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
//    this.modMenuC = new CtrlMenu();
  }
  
  public void iniciar(){
    frm.setTitle("Registro de Escuelas");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
  }
  
  @Override
  public void actionPerformed (ActionEvent e){
    if (e.getSource() == frm.btnRegistrar){
      mod.setCodigo(frm.txtCodigo.getText());
      mod.setNombre(frm.txtNombre.getText());
      
      if(modC.registar(mod)){
        JOptionPane.showMessageDialog(null, "Registro de escuela guardado");
        limpiar();
    }else{
        JOptionPane.showMessageDialog(null, "ERROR al guardar  ");
        limpiar();
      } 
    }
    
     if (e.getSource() == frm.btnBuscar){
      mod.setCodigo(frm.txtCodigo.getText());

      if(modC.buscar(mod)){
        frm.txtCodigo.setText(mod.getCodigo());
        frm.txtNombre.setText(mod.getNombre());
//        limpiar();
    }else{
        JOptionPane.showMessageDialog(null, "ERROR al buscar escuela  ");
        limpiar();
      }
    }
     
     if(e.getSource() == frm.btnLimpiar){
       frm.txtCodigo.setText("");
       frm.txtNombre.setText("");
     }

     if(e.getSource() == frm.btnMenu){
       frm.setVisible(false);
       CtrlMenu modMenuC = new CtrlMenu();
       modMenuC.iniciar();
     }
  }
  
  public void limpiar(){
    frm.txtCodigo.setText(null);
    frm.txtNombre.setText(null);

  } 
}
