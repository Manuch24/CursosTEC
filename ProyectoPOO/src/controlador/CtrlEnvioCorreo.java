package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import vista.FrmConsultarPlan;
import vista.FrmEnvioCorreo;
import vista.FrmMenu;

/**
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class CtrlEnvioCorreo implements ActionListener {

    //variables
    private FrmEnvioCorreo frmenviar;
    private PlanEstudioDAO planEstudioDAO;
    private FrmConsultarPlan frm;

    /**
     * Constructor de la clase controlador de la ventana que envia correos
     *
     * @param frmenviar El form que maneja este controlador
     * @param planEstudioDAO EL DAO que maneja los planes de estudio
     * @param frm El form que maneja este controlador
     */
    public CtrlEnvioCorreo(FrmEnvioCorreo frmenviar, PlanEstudioDAO planEstudioDAO, FrmConsultarPlan frm) {
        this.frmenviar = frmenviar;
        this.planEstudioDAO = planEstudioDAO;
        this.frm = frm;
        //this.ctrlconsultarplan = new CtrlConsultarPlan(escuelaDAO,planEstudioDAO,frm);

        this.frmenviar.BtnEnviarCorreo.addActionListener(this);
        this.frmenviar.BtnVolver.addActionListener(this);
    }

    /**
     * Método para inicializar la ventana
     */
    public void iniciar() {
        frmenviar.setTitle("Enviar correo");
        frmenviar.setVisible(true);
        frmenviar.setLocationRelativeTo(null);
    }

    /**
     * Método que envia los datos necesarios para enviar el correo
     */
    public void enviarCorreo() {
        planEstudioDAO.EnviarEmail(frmenviar.TxtEmailDestino.getText());
    }

    @Override
     /**
     * Método donde se programan las acciones de cada uno de los botones o
     * eventos
     * 
     *  @param ActionEvent Es la accion que se aplica al boton
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmenviar.BtnEnviarCorreo) {
            enviarCorreo();
        }

        if (e.getSource() == frmenviar.BtnVolver) {
            frmenviar.setVisible(false);
            frmenviar.dispose();
            //frmenviar.set
            //CtrlConsultarPlan ctrlConsultaPlan = new CtrlConsultarPlan();
            //CtrlMenu.iniciar();
        }
    }
}
