/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FrmMenu;

/**
 *
 * @author XT
 */
public class CtrlMenu implements ActionListener{
  private FrmMenu frmMenu;
  
  public CtrlMenu(){
    this.frmMenu = new FrmMenu();
  }
  
  public void iniciar(){
    frmMenu.setVisible(true);
    frmMenu.setLocationRelativeTo(null);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
