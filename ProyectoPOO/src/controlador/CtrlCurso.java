package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logicaNegocios.CursoDAO;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.Curso;
import vista.FrmCursos;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class CtrlCurso implements ActionListener{
  private Curso curso;
  private CursoDAO cursoDAO;
  private FrmCursos frm;
  private EscuelaDAO escuelaDAO;
  
  public CtrlCurso(){
    this.frm = new FrmCursos();
    this.curso = new Curso();
    this.cursoDAO = new CursoDAO();
    this.escuelaDAO = new EscuelaDAO();
  }
  
  public CtrlCurso(Curso curso, CursoDAO cursoDAO, FrmCursos frm ){
    this.curso = curso;
    this.cursoDAO = cursoDAO;
    this.frm = frm; 
    this.escuelaDAO = new EscuelaDAO();
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
    frm.lblCodigo.setText(escuelaDAO.buscarCodigo(frm.cbxEscuela.getSelectedItem().toString()));
  }
  
  public void iniciar(){
    frm.setTitle("Registro de Cursos");
    frm.setVisible(true);
    frm.setLocationRelativeTo(null);

  }
  
  public void llenadoCbxEscuelas(){
    escuelaDAO.listarEscuelas(frm.cbxEscuela);
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
      curso.setCodigo(frm.lblCodigo.getText() + frm.txtCodigo.getText());
      curso.setNombre(frm.txtNombre.getText());
      curso.setHorasLectivas( Integer.parseInt(frm.cbxCreditos.getSelectedItem().toString()));
      curso.setCreditos( Integer.parseInt(frm.cbxHorasLectivas.getSelectedItem().toString()));
      
      if(cursoDAO.registar(curso)){
        JOptionPane.showMessageDialog(null, "Registro del  curso " +curso.getNombre()+ " guardado");
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
