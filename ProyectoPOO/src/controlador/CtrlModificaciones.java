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
import logicaNegocios.PlanEstudio;
import logicaNegocios.CursoDAO;
import logicaNegocios.Curso;
import vista.FrmModificaciones;
/**
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class CtrlModificaciones implements ActionListener{
    private Curso curso;
    private PlanEstudio planestudio;
    private FrmModificaciones frm;
    private CursoDAO cursoDAO;
    private PlanEstudioDAO planEstudioDAO;

    public CtrlModificaciones(Curso curso, PlanEstudio planestudio, CursoDAO cursoDAO, PlanEstudioDAO planEstudioDAO,FrmModificaciones frm) {
        this.curso = curso;
        this.planestudio = planestudio;
        this.cursoDAO = cursoDAO;
        this.planEstudioDAO = planEstudioDAO;
        this.frm = frm;
        
        this.frm.BtnBuscarRequi.addActionListener(this);
        this.frm.BtnVolver.addActionListener(this);
        this.frm.BtnBorrar.addActionListener(this);
        this.frm.BtnBuscarPlan.addActionListener(this);
        this.frm.BtnBorrarCursoPlan.addActionListener(this);
        //this.frm.TxtConsulta1.addActionListener(this);
        
    }
    
    public void iniciar() {
        frm.setTitle("Modificaciones adicionales");
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        
    }
    
    public void llenartabla(){
        cursoDAO.consultarRequisitos(frm.TxtConsulta1.getText(),frm.JTableElmRequisitos);
    }
    
    public void borrarRequisito(){
        cursoDAO.BorrarRequisitos(frm.JTableElmRequisitos);
        llenartabla();
    }
    
    public void llenartablaPlan(){
        planEstudioDAO.consultarPlanEstudio(frm.TxtPlan.getText(), frm.jTablePlan);
    }
    
    public void borrarCursoPlan(){
         planEstudioDAO. borrarCursoPlan(frm.TxtPlan.getText(), frm.jTablePlan);
         llenartablaPlan();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == frm.BtnBuscarRequi){
            llenartabla();
        }
        
        if(e.getSource() == frm.BtnVolver) {
            frm.setVisible(false);
            CtrlMenu ctrlMenu = new CtrlMenu();
            ctrlMenu.iniciar();
        }
        
        if(e.getSource() == frm.BtnBorrar){
            borrarRequisito();
        }
        
        if(e.getSource() == frm.BtnBuscarPlan){
            llenartablaPlan();
        }
        
        if(e.getSource() == frm.BtnBorrarCursoPlan){
            borrarCursoPlan();
        }
    }
    
    
}
