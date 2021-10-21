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
public class CtrlCurso implements ActionListener {

    // Variables
    private Curso curso;
    private CursoDAO cursoDAO;
    private FrmCursos frm;
    private EscuelaDAO escuelaDAO;

    /**
     * Constructor vacio de la clase controlador del curso
     */
    public CtrlCurso() {
        this.frm = new FrmCursos();
        this.curso = new Curso();
        this.cursoDAO = new CursoDAO();
        this.escuelaDAO = new EscuelaDAO();
    }

    /**
     * Constructor de la clase controlador del curso
     *
     * @param curso Modelo del curso
     * @param cursoDAO EL DAO que maneja los cursos
     * @param frm El form que maneja este controlador
     */
    public CtrlCurso(Curso curso, CursoDAO cursoDAO, FrmCursos frm) {
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

    /**
     * Método el cual llena los espacios con los codigos de las escuelas
     */
    public void setLetrasCodigo() {
        frm.lblCodigo.setText(escuelaDAO.buscarCodigo(frm.cbxEscuela.getSelectedItem().toString()));
    }

    /**
     * Método para inicializar la ventana del curso
     */
    public void iniciar() {
        frm.setTitle("Registro de Cursos");
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);

    }

    /**
     * Método que llena el combobox de la escuela
     */
    public void llenadoCbxEscuelas() {
        escuelaDAO.listarEscuelas(frm.cbxEscuela);
    }

    /**
     * Método que limpia la ventana
     */
    private void limpiar() {
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.cbxCreditos.getSelectedIndex();
        frm.cbxEscuela.getSelectedIndex();
        frm.cbxHorasLectivas.getSelectedIndex();

    }

    @Override
    /**
     * Método donde se programan las acciones de cada uno de los botones o
     * eventos
     *
     * @param ActionEvent Es la accion que se aplica al boton
     */
    public void actionPerformed(ActionEvent e) {
        //LimpiarCampos
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
        //Registar curso
        if (e.getSource() == frm.cbxEscuela) {
            setLetrasCodigo();
        }
        if (e.getSource() == frm.btnRegistar) {
            setLetrasCodigo();
            curso.setCodigo(frm.lblCodigo.getText() + frm.txtCodigo.getText());
            curso.setNombre(frm.txtNombre.getText());
            curso.setHorasLectivas(Integer.parseInt(frm.cbxCreditos.getSelectedItem().toString()));
            curso.setCreditos(Integer.parseInt(frm.cbxHorasLectivas.getSelectedItem().toString()));

            if (cursoDAO.registar(curso)) {
                JOptionPane.showMessageDialog(null, "Registro del  curso " + curso.getNombre() + " guardado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar curso  ");
            }
        }

        //Volver al menu
        if (e.getSource() == frm.btnVolver) {
            frm.setVisible(false);
            //Se inicializa el ctrlMenu aquí porque se tiene problemas desde el constructor
            CtrlMenu ctrlMenu = new CtrlMenu();
            ctrlMenu.iniciar();
        }
    }
}
