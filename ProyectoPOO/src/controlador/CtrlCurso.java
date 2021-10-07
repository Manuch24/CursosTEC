/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.ConsultasCurso;
import logicaNegocios.ConsultasEscuela;
import logicaNegocios.Curso;
import vista.FrmCursos;

/**
 *
 * @author XT
 */
public class CtrlCurso implements ActionListener{
  private Curso mod;
  private ConsultasCurso consultCuso;
  private FrmCursos frm;
  private CtrlMenu modMenuC; //Para regresar al menu
  private ConsultasEscuela consultEscuela;
  
  public CtrlCurso(){
    this.frm = new FrmCursos();
    this.mod = new Curso();
    this.consultCuso = new ConsultasCurso();
    this.consultEscuela = new ConsultasEscuela();
    
  }
  
  public void iniciar(){
    frm.setTitle("Registro de Cursos");
    frm.setLocationRelativeTo(null);
    //frm.comBoxEscuela.ad
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  
}
