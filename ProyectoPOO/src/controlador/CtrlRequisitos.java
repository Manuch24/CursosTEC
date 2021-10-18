package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logicaNegocios.ConsultasCurso;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.ConsultasPlan;
import logicaNegocios.Curso;
import vista.FrmRequisitos;


/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlRequisitos implements ActionListener{
  private ConsultasEscuela consultasEscuela;
  private Curso mod;
  private FrmRequisitos frm;
  private ConsultasCurso modC;
  private ConsultasPlan consultasPlan;
  
  public CtrlRequisitos(Curso mod, ConsultasCurso modC, FrmRequisitos frm){
    this.consultasEscuela = new ConsultasEscuela();
    this.consultasPlan = new ConsultasPlan();
    this.mod = mod;
    this.modC =  modC;
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
  
   public void llenadoCbxEscuelas(){
    consultasEscuela.listarEscuelas(this.frm.cbxEscuela);
  }
   
   public void llenadoCbxCursosEscuela(){
     modC.listarCursoEscuela(this.frm.cbxEscuela,this.frm.cbxCurso);
   }
   
   public void llenadoCbxRequisitos(){
//     modC.listarCursosRequisitos(this.frm.cbxRequisito,this.frm.cbxCurso);
//     modC.listarCursosRequisitos(this.frm.cbxCorrequisito, this.frm.cbxCurso);
     modC.listarCursos(this.frm.cbxRequisito);
     modC.listarCursos(this.frm.cbxCorrequisito);
   }
   

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
      try {
        if (modC.registrarRequisito(frm.cbxCurso, frm.cbxRequisito)) {
          JOptionPane.showMessageDialog(null, "Requisito Guardado  ");
        } else {
          JOptionPane.showMessageDialog(null, "Error al guardar, no se puede guardar el mismo requisito  ");
        }
      } catch (SQLException ex) {
        Logger.getLogger(CtrlRequisitos.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    if (e.getSource() == frm.btnRegistrarCorrequisito) {
      try {
        if (modC.registrarCorrequisito(frm.cbxCurso, frm.cbxCorrequisito)) {
          JOptionPane.showMessageDialog(null, "Requisito Guardado  ");
        } else {
          JOptionPane.showMessageDialog(null, "Error al guardar, no se puede guardar el mismo Corrrequisito  ");
        }
      } catch (SQLException ex) {
        Logger.getLogger(CtrlRequisitos.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }  
  
}