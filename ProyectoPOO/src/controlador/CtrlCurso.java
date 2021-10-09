package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logicaNegocios.ConsultasCurso;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.Curso;
import vista.FrmCursos;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlCurso implements ActionListener{
  private Curso mod;
  private ConsultasCurso modC;
  private FrmCursos frm;
  private CtrlMenu ctrlMenu; //Para regresar al menu
  private ConsultasEscuela consultasEscuela;
  
  public CtrlCurso(){
    this.frm = new FrmCursos();
    this.mod = new Curso();
    this.modC = new ConsultasCurso();
    this.consultasEscuela = new ConsultasEscuela();
  }
  
  public CtrlCurso(Curso mod, ConsultasCurso modC, FrmCursos frm ){
    this.mod = mod;
    this.modC = modC;
    this.frm = frm; 
    this.consultasEscuela = new ConsultasEscuela();
    this.frm.txtNombre.addActionListener(this);
    this.frm.btnVolver.addActionListener(this);
    this.frm.txtCodigo.addActionListener(this);
    this.frm.btnRegistar.addActionListener(this);
    this.frm.btnLimpiar.addActionListener(this); 
    this.frm.cbxEscuela.addActionListener(this);
    llenadoCbxEscuelas();
    setLetrasCodigo();
  }
  
  public void setLetrasCodigo(){
    frm.lblCodigo.setText(consultasEscuela.buscarCodigo(frm.cbxEscuela.getSelectedItem().toString()));
  }
  
  public void iniciar(){
    frm.setTitle("Registro de Cursos");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);
    //Llenado comboBox con el nombre de las escuelas
    //llenadoCbxEscuelas();

  }
  
  public void llenadoCbxEscuelas(){
    consultasEscuela.listarEscuelas(this.frm.cbxEscuela);
  }
  
  private void limpiar() {
    frm.txtCodigo.setText(null);
    frm.txtNombre.setText(null);
    frm.cbxCreditos.getSelectedIndex();
    frm.cbxEscuela.getSelectedIndex();
    frm.cbxHorasLectivas.getSelectedIndex();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //LimpiarCampos
    if(e.getSource()==frm.btnLimpiar){
      limpiar();
    }
    //Registar curso
    if(e.getSource() == frm.cbxEscuela){
      setLetrasCodigo();
    }
    if (e.getSource()==frm.btnRegistar){
      setLetrasCodigo();
      mod.setCodigo(frm.lblCodigo.getText() + frm.txtCodigo.getText());
      mod.setNombre(frm.txtNombre.getText());
      mod.setCreditos( Integer.parseInt(frm.cbxCreditos.getSelectedItem().toString()));
      mod.setCreditos( Integer.parseInt(frm.cbxHorasLectivas.getSelectedItem().toString()));
      if(modC.registar(mod)){
        JOptionPane.showMessageDialog(null, "Registro del  curso " +mod.getNombre()+ " guardado");
      }else{
        JOptionPane.showMessageDialog(null, "Error al guardar curso  ");
      } 
    }   
    
    //Volver al menu
    if(e.getSource()==frm.btnVolver){
      frm.setVisible(false);
      //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
      CtrlMenu ctrlMenu = new CtrlMenu();
      ctrlMenu.iniciar();
    }
  }  
}
