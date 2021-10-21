/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import vista.FrmConsultarPlan;
import vista.FrmEnvioCorreo;
import vista.FrmMenu;

/**
 *
 * @author manue
 */
public class CtrlEnvioCorreo implements ActionListener{


    private FrmEnvioCorreo frmenviar;
    
    

    private PlanEstudioDAO planEstudioDAO;
    
    private FrmConsultarPlan frm;


    public CtrlEnvioCorreo(FrmEnvioCorreo frmenviar, PlanEstudioDAO planEstudioDAO,FrmConsultarPlan frm) {
        this.frmenviar = frmenviar;
        this.planEstudioDAO = planEstudioDAO;
        this.frm = frm;
        //this.ctrlconsultarplan = new CtrlConsultarPlan(escuelaDAO,planEstudioDAO,frm);
        
        this.frmenviar.BtnEnviarCorreo.addActionListener(this);
        this.frmenviar.BtnVolver.addActionListener(this);
    }

    public void iniciar() {
        frmenviar.setTitle("Enviar correo");
        frmenviar.setVisible(true);
        frmenviar.setLocationRelativeTo(null);
    }
    
    public void enviarCorreo(){
        planEstudioDAO.EnviarEmail(frmenviar.TxtEmailDestino.getText());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == frmenviar.BtnEnviarCorreo){
            enviarCorreo();
        }
        
        if(e.getSource() == frmenviar.BtnVolver){
            frmenviar.setVisible(false);
            frmenviar.dispose();
            //frmenviar.set
            //CtrlConsultarPlan ctrlConsultaPlan = new CtrlConsultarPlan();
            //CtrlMenu.iniciar();
        }
    }
}
