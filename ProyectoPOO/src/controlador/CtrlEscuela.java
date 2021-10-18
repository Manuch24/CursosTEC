package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logicaNegocios.EscuelaDAO;
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
  private Escuela escuela;
  private EscuelaDAO escuelaDAO;
  private FrmEscuela frm;
  private CtrlMenu ctrlMenu;

  public CtrlEscuela(Escuela mod, EscuelaDAO escuelaDAO, FrmEscuela frm) {
    this.escuela = mod;
    this.escuelaDAO = escuelaDAO;
    this.frm = frm;
    this.frm.btnBuscar.addActionListener(this);
    this.frm.btnMenu.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
    this.ctrlMenu = new CtrlMenu();
  }

  CtrlEscuela() {
    this.escuela = new Escuela();
    this.frm = new FrmEscuela();
    this.escuelaDAO = new EscuelaDAO();
    this.frm.btnBuscar.addActionListener(this);
    this.frm.btnMenu.addActionListener(this);
    this.frm.btnRegistrar.addActionListener(this);
  }
  
  /**
   * Método que inicia el formulario
   */
  public void iniciar(){
    frm.setTitle("Registro de Escuelas");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
  }
  
  @Override
  /**
   * Método donde se programan las acciones de cada uno de los botones o eventos
   */
  public void actionPerformed (ActionEvent e){
    if (e.getSource() == frm.btnRegistrar){
      escuela.setCodigo(frm.txtCodigo.getText());
      escuela.setNombre(frm.txtNombre.getText());
      
      if(escuelaDAO.registar(escuela)){
        JOptionPane.showMessageDialog(null, "Registro de escuela guardado");
        limpiar();
    }else{
        JOptionPane.showMessageDialog(null, "ERROR al guardar  ");
        limpiar();
      } 
    }
    
     if (e.getSource() == frm.btnBuscar){
      escuela.setCodigo(frm.txtCodigo.getText());
      
      if(escuelaDAO.buscar(escuela)){
        frm.txtCodigo.setText(escuela.getCodigo());
        frm.txtNombre.setText(escuela.getNombre());
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
