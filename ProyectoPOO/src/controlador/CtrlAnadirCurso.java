package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import logicaNegocios.CursoDAO;
import logicaNegocios.EscuelaDAO;
import logicaNegocios.PlanEstudioDAO;
import logicaNegocios.Curso;
import logicaNegocios.PlanEstudio;
import vista.FrmAnadirCurso;

/**
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class CtrlAnadirCurso implements ActionListener {

    private FrmAnadirCurso frm;
    private CursoDAO cursoDAO;
    private PlanEstudioDAO planEstudioDAO;
    private Curso curso;
    private PlanEstudio planEstudio;
    private EscuelaDAO escuelaDAO;

    /**
     * Constructor vacio
     */
    public CtrlAnadirCurso() {

    }

    /**
     * Constructor de la clase controlador Anadir curso
     *
     * @param curso Modelo del curso
     * @param planEstudio modelo de plan de estudio
     * @param cursoDAO EL DAO que maneja los cursos
     * @param planEstudioDAO EL DAO que maneja los planes de estudio
     * @param frm El form que maneja este controlador
     */
    public CtrlAnadirCurso(Curso curso, PlanEstudio planEstudio, CursoDAO cursoDAO, PlanEstudioDAO planEstudioDAO, FrmAnadirCurso frm) {
        this.cursoDAO = cursoDAO;
        this.planEstudioDAO = planEstudioDAO;
        this.planEstudio = planEstudio;
        this.curso = curso;
        this.frm = frm;
        this.escuelaDAO = new EscuelaDAO();
        this.frm.btnVolver.addActionListener(this);
        this.frm.cbxEscuela.addActionListener(this);
        this.frm.cbxPlan.addActionListener(this);
        this.frm.btnRegistrar.addActionListener(this);
    }

    /**
     * Método para inicalizar la ventana
     */
    public void iniciar() {
        frm.setTitle("Registro de Planes");
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        llenadoCbxEscuelas();
        llenadoCbxPlan();
        llenadoCbxCurso();
    }

    /**
     * Método que llena el combobox de la escuela
     */
    public void llenadoCbxEscuelas() {
        escuelaDAO.listarEscuelas(frm.cbxEscuela);
    }

    /**
     * Método que llena el combobox de los planes de estudio
     */
    public void llenadoCbxPlan() {
        planEstudioDAO.listarPlanes(frm.cbxPlan, frm.cbxEscuela);
    }

    /**
     * Método que llena el combobox del curso
     */
    public void llenadoCbxCurso() {
        cursoDAO.listarCursos(frm.cbxCurso);
    }

    /**
     * *
     * Metodo que valida si el curso ya existe en el plan seleccionado
     *
     * @param cbxCurso codigo del curso del JComboBox curso
     * @param cbxPlan Item seleccionado del JComboBox plan
     * @return true si existe, false si no existe
     *
     */
    public boolean existeCurso(JComboBox cbxCurso, JComboBox cbxPlan) {
        String codigoCurso = cbxCurso.getSelectedItem().toString();
        String numPlan = cbxPlan.getSelectedItem().toString();

        if (planEstudioDAO.existeCursoPlan(codigoCurso, numPlan) == "No existe") {
            return false;
        } else {
            return true;
        }
    }

    @Override
    /**
     * Método donde se programan las acciones de cada uno de los botones o
     * eventos
     *
     * @param ActionEvent Es la accion que se aplica al boton
     */
    public void actionPerformed(ActionEvent e) {
        //Volver al menu
        if (e.getSource() == frm.btnVolver) {
            frm.setVisible(false);
            //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
            CtrlMenu ctrlMenu = new CtrlMenu();
            ctrlMenu.iniciar();
        }
        if (e.getSource() == frm.cbxEscuela) {
            llenadoCbxPlan();
        }
        if (e.getSource() == frm.btnRegistrar) {
            if (existeCurso(frm.cbxCurso, frm.cbxPlan) == true) {
                JOptionPane.showMessageDialog(null, "El curso ya se encuentra en el plan " + frm.cbxPlan.getSelectedItem().toString()
                        + " en el bloque " + frm.cbxBloque.getSelectedItem().toString());
            } else {
                planEstudioDAO.ingresarCurso(frm.cbxBloque.getSelectedItem().toString(), frm.cbxPlan.getSelectedItem().toString(),
                        frm.cbxCurso.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Curso Ingresado al plan: " + frm.cbxPlan.getSelectedItem().toString() + " en el bloque "
                        + frm.cbxBloque.getSelectedItem().toString());
            }
        }
    }
}
