package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static logicaNegocios.Conexion.getConexion;
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
public class CtrlModificaciones implements ActionListener {

    //variables
    private Curso curso;
    private PlanEstudio planestudio;
    private FrmModificaciones frm;
    private CursoDAO cursoDAO;
    private PlanEstudioDAO planEstudioDAO;

    /**
     * Constructor de la clase controlador de las modificaciones y consultas
     *
     * @param curso Modelo del curso
     * @param planestudio Modelo del plan de estudio
     * @param cursoDAO EL DAO que maneja los cursos
     * @param planEstudioDAO planEstudioDAO EL DAO que maneja los planes de
     * estudio
     * @param frm La vista que maneja este controlador
     */
    public CtrlModificaciones(Curso curso, PlanEstudio planestudio, CursoDAO cursoDAO, PlanEstudioDAO planEstudioDAO, FrmModificaciones frm) {
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
        this.frm.BtnMostarCursos.addActionListener(this);
        this.frm.BtnEliminarCurso.addActionListener(this);
        this.frm.BtnBuscar6.addActionListener(this);
        //this.frm.TxtConsulta1.addActionListener(this);

    }

    /**
     * Método para inicializar la ventana modificaciones
     */
    public void iniciar() {
        frm.setTitle("Modificaciones adicionales");
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);

    }

    /**
     * Método el cual llena la tabla de la consulta de los requisistos y
     * correcquisitos
     */
    public void llenartabla() {
        cursoDAO.consultarRequisitos(frm.TxtConsulta1.getText(), frm.JTableElmRequisitos);
        cursoDAO.consultarCorrequistos(frm.TxtConsulta1.getText(), frm.jTableCorrequi);
    }

    /**
     * Método el cual borra el requsito deseado
     */
    public void borrarRequisito() {
        cursoDAO.BorrarRequisitos(frm.JTableElmRequisitos);
        llenartabla();
    }

    /**
     * Método el cual llena la tabla de la consulta de los planes de estudio
     */
    public void llenartablaPlan() {
        planEstudioDAO.consultarPlanEstudio(frm.TxtPlan.getText(), frm.jTablePlan);
    }

    /**
     * Método el cual borra el Plan deseado
     */
    public void borrarCursoPlan() {
        planEstudioDAO.borrarCursoPlan(frm.TxtPlan.getText(), frm.jTablePlan);
        llenartablaPlan();
    }

    /**
     * Método el cual llena la tabla de la consulta los cursos
     */
    public void llenartablaCursos() {
        cursoDAO.consultarCursos(frm.jTableCursos);
    }

    /**
     * Método el cual borra el curso del plan
     */
    public void eliminarCurso() {
        cursoDAO.eliminarCurso(frm.jTableCursos);
    }

    /**
     * Método el cual llena la tabla delos planes que pertenece un curso
     */
    public void llenartablaCursoPlan() {
        cursoDAO.ConsultarCursosEnPlan(frm.TxtNombreCurso.getText(), frm.JTableConsulta6);
    }

    @Override
    /**
     * Método donde se programan las acciones de cada uno de los botones o
     * eventos
     *
     * @param ActionEvent Es la accion que se aplica al boton
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.BtnBuscarRequi) {
            llenartabla();
        }

        if (e.getSource() == frm.BtnVolver) {
            frm.setVisible(false);
            CtrlMenu ctrlMenu = new CtrlMenu();
            ctrlMenu.iniciar();
        }

        if (e.getSource() == frm.BtnBorrar) {
            borrarRequisito();
        }

        if (e.getSource() == frm.BtnBuscarPlan) {
            llenartablaPlan();
        }

        if (e.getSource() == frm.BtnBorrarCursoPlan) {
            borrarCursoPlan();
        }

        if (e.getSource() == frm.BtnMostarCursos) {
            llenartablaCursos();
        }

        if (e.getSource() == frm.BtnBuscar6) {
            llenartablaCursoPlan();
        }

        if (e.getSource() == frm.BtnEliminarCurso) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection conn = getConexion();

            try {
                int row = frm.jTableCursos.getSelectedRow();
                String dato = (frm.jTableCursos.getModel().getValueAt(row, 0).toString());
                String Consulta = "select Curso.codigoCurso, Curso.nombreCurso, PlanEstudio.numPlan from Curso\n"
                        + "inner join Bloque on Bloque.codigoCurso=Curso.codigoCurso\n"
                        + "inner join PlanEstudio on PlanEstudio.numPlan=Bloque.numPlan where Curso.codigoCurso = " + "'" + dato + "'";
                ps = conn.prepareStatement(Consulta);
                rs = ps.executeQuery();

                if (!rs.next()) {
                    eliminarCurso();
                    JOptionPane.showMessageDialog(null, "El curso se eliminó correctamente");
                    llenartablaCursos();
                } else {
                    JOptionPane.showMessageDialog(null, "El curso se encuentra asociado a un plan de estudios");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrlModificaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
