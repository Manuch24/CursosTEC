
package logicaNegocios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static logicaNegocios.Conexion.getConexion;
import vista.FrmConsultarPlan;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class PlanEstudioDAO extends Conexion {
    static String nombreDocumetoTodo;
    private FrmConsultarPlan frmConsultarPlan;

    /**
     * Metiodo para registrar plan de estudio
     * @param pPlanEstudio Es el modelo del plan de estudio 
     * @param cbxEscuela Combobox de la escuela
     * @param date Fecha de vigencia del plan
     * @return true si se registra correctamente y false en caso qe no se haya registrado
     */
    public boolean registrar(PlanEstudio pPlanEstudio, JComboBox cbxEscuela, String date) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "{call insertarPlan (?,?,?)}";

        try {
            ps = con.prepareStatement(sql);
            EscuelaDAO consultasEscuela = new EscuelaDAO();
            java.sql.Date date1 = java.sql.Date.valueOf(date);

            ps.setString(1, consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString()));
            ps.setString(2, String.valueOf(pPlanEstudio.getNumPlan()));
            ps.setDate(3, date1);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros enteros");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Método el realiza consultas sobre los planes de estudio y llena los
     * combobox
     * @param cbxPlanes El combobox de los planes de estudio
     * @param cbxEscuela El comobox de las escuelas
     */
    public void listarPlanes(JComboBox cbxPlanes, JComboBox cbxEscuela) {
        cbxPlanes.removeAllItems();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        //Se llama a consultasEscuela
        EscuelaDAO consultasEscuela = new EscuelaDAO();

        String sql = "SELECT numPlan FROM EscuelaPlan WHERE codigoEscuela =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, consultasEscuela.buscarCodigo(cbxEscuela.getSelectedItem().toString()));
            rs = ps.executeQuery();

            while (rs.next()) {
                cbxPlanes.addItem(rs.getString("numPlan"));
//       pEscuela.setNombre(rs.getString("nombre"))
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
 
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Método el cual verfica si existe un plan registrado
     * @param numPlan El número de plan el cual quiere ser consultado
     * @return el numero del plan si se esta registrado, y en caos que no lo este lo indica
     */
    public String numeroPlan(String numPlan) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT numPlan FROM PlanEstudio WHERE numPlan = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, numPlan);
            rs = ps.executeQuery();
            if (rs.next()) {
                String numPlanBD = (rs.getString("numPlan"));
                return numPlanBD;
            }
            rs.close();
            return "No existe";
        } catch (SQLException e) {
            System.err.println(e);
            return "No existe";
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Método que verifca que si existe un curso en el plan
     * @param codigoCurso Es el cpdigo del cruso que se va a verficar
     * @param numPlan Es el codigo del plan donde se va a buscar
     * @return Si esta retorna el curso, si no lo esta, dira que no existe
     */
    public String existeCursoPlan(String codigoCurso, String numPlan) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM Bloque WHERE codigoCurso = ? AND numPlan = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoCurso);
            ps.setString(2, numPlan);
            rs = ps.executeQuery();
            if (rs.next()) {
                String codigoCursoBD = (rs.getString("codigoCurso"));
                return codigoCursoBD;
            }
            rs.close();
            return "No existe";
        } catch (SQLException e) {
            System.err.println(e);
            return "No existe";
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    /**
     * Metodo en el cual se va a registrar un curso a un plan y blque
     * @param numBloque El numero del bloque al cual va  a pertencer
     * @param numPlan Es el plan al cual va a pertencer el curso
     * @param codigoCurso Es el codigo del curso que se va agragar
     * @return true si se registra correctamente
     */
    public boolean ingresarCurso(String numBloque, String numPlan, String codigoCurso) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO Bloque (numBloque,numPlan,codigoCurso) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(numBloque));
            ps.setString(2, numPlan);
            ps.setString(3, codigoCurso);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Métood en el cual se va a consultar el plan de estudiops
     * @param numPlan El numero dekl plan el cual se va a consultar
     * @param JTableBloque la tabla donde se muestra la consulta
     * @param LabelFecha Es la fecha de vigencia desde la consulta
     */
    public void consultarPlan(String numPlan, JTable JTableBloque,JLabel LabelFecha) {
        nombreDocumetoTodo = numPlan;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT Bloque.codigoCurso, Curso.nombreCurso, Bloque.numBloque, Curso.horaslectivas, Curso.creditos,PlanEstudio.vigencia\n" +
            "FROM Bloque INNER JOIN Curso ON Bloque.codigoCurso = Curso.codigoCurso\n"
                + "inner join PlanEstudio on PlanEstudio.numPlan = Bloque.numPlan WHERE Bloque.numPlan = ?\n"
                + "ORDER BY Bloque.numBloque ASC";

        try {
            //Se declara el modelo de la tabla
            DefaultTableModel modelo = new DefaultTableModel();
            JTableBloque.setModel(modelo);
//      String numPlan = cbxPlan.getSelectedItem().toString();
            ps = con.prepareStatement(sql);
            ps.setString(1, numPlan);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantColumnas = rsMd.getColumnCount();
            
            String fecha = "";
            modelo.addColumn("codigoCurso");
            modelo.addColumn("nombreCurso");
            modelo.addColumn("numBloque");
            modelo.addColumn("horasLectivas");
            modelo.addColumn("creditos");

            while (rs.next()) {
                Object[] filas = new Object[cantColumnas];
                fecha = rs.getString(6);
                for (int i = 0; i < cantColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
            LabelFecha.setText(fecha);
            //System.out.println(fecha);
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    /**
     * Método en el cual se consulta cantidad de curso y suma de creditos
     * @param numPlan el numero del plan a buscar
     * @param LabelCursos DOnde se muestra la cantidad de cursos
     * @param LabelCreditos donde se muestra la suma de creditos
     */
    public void contadores(String numPlan, JLabel LabelCursos, JLabel LabelCreditos) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
           
            Connection conn = getConexion();
            String consulta2 = "select count(Curso.codigoCurso) as cantidadCursos, SUM(creditos) as TotalCreditos from PlanEstudio\n"
                    + "inner join Bloque on PlanEstudio.numPlan=Bloque.numPlan\n"
                    + "inner join Curso on Curso.codigoCurso = Bloque.codigoCurso and PlanEstudio.numPlan = ?";

            ps = conn.prepareStatement(consulta2);
            ps.setString(1, numPlan);
            
            rs = ps.executeQuery();
           
            String CantCursos = "";
            String sumaCreditos = "";

            while (rs.next()) {
                CantCursos = rs.getString(1);
                sumaCreditos = rs.getString(2);
            }

            System.out.println(CantCursos);
            System.out.println(sumaCreditos);
            
            LabelCursos.setText(CantCursos);
            LabelCreditos.setText(sumaCreditos);
            
            System.out.println("entra aqu4");
        } catch (Exception e) {

        }
    }

    /**
     * Método el a extraer informacion para llenar pdf
     * @param numPlan numero del plan que se consulta
     * @param jTablePlan la plaba donde se aloja la informacion
     */
    public void consultarPlanEstudio(String numPlan, JTable jTablePlan) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String consulta = "select Curso.codigoCurso, Curso.nombreCurso, PlanEstudio.numPlan from Curso\n"
                + "inner join Bloque on Bloque.codigoCurso=Curso.codigoCurso\n"
                + "inner join PlanEstudio on PlanEstudio.numPlan=Bloque.numPlan where PlanEstudio.numPlan = ?";

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTablePlan.setModel(modelo);

            ps = con.prepareStatement(consulta);
            ps.setString(1, numPlan);

            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantColumnas = rsMd.getColumnCount();

            modelo.addColumn("Codigo Curso");
            modelo.addColumn("Nombre Curso");
            modelo.addColumn("Numero de plan");

            while (rs.next()) {
                Object[] filas = new Object[cantColumnas];

                for (int i = 0; i < cantColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Método donde se va a borrar el curso de un plan
     * @param numplan  el numero del plan para buscarlo
     * @param jTablePlan la tabla en el cual esta alojada la informacion
     */
    public void borrarCursoPlan(String numplan, JTable jTablePlan) {
        Connection con = getConexion();

        int row = jTablePlan.getSelectedRow();

        String valor = (jTablePlan.getModel().getValueAt(row, 0).toString());

        String query = "delete from Bloque where codigoCurso =" + "'" + valor + "'" + "and numPlan =" + "'" + numplan + "'";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();

            DefaultTableModel model = (DefaultTableModel) jTablePlan.getModel();
            model.setRowCount(0);

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo donde se va a crear el pdf
     * @return La ruta donde se guarda el pdf
     * @throws SQLException error con sql
     */
    public String crearPDF() throws SQLException {
        Connection con = getConexion();

        String nombre = nombreDocumetoTodo;
        System.out.println(nombre);
        String nombreDocumento = "Plan " + nombre;
        String ruta = "C:\\Docs\\" + nombreDocumento;

        String consulta = "SELECT Bloque.codigoCurso, Curso.nombreCurso, Bloque.numBloque, Curso.horaslectivas, Curso.creditos\n"
                + "FROM Bloque INNER JOIN Curso ON Bloque.codigoCurso = Curso.codigoCurso WHERE Bloque.numPlan = ?\n"
                + " ORDER BY Bloque.numBloque ASC";

        String encabezado = "A continuación se desglosa detalladamente los cursos del plan de Estudio " + nombre;
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        try {
            FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            //System.out.println("hola");
            documento.open();
            documento.add(new Paragraph(encabezado));
            while (rs.next()) {
                //System.out.println("hola2");
                Paragraph para = new Paragraph("CodigoCurso: " + rs.getString(1) + "| NombreCurso: " + rs.getString(2) + "| NumeroBloque: " + rs.getInt(3) + "| horaslectivas: " + rs.getInt(4) + "| creditos: " + rs.getInt(5));
                documento.add(para);
                documento.add(new Paragraph("---------------------------------------"));
            }
            documento.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return ruta + ".pdf";
    }

    /**
     * Metodo para construir el email.
     * @param destinatario el destinatario al cual se quiere enviar el ccrreo
     * @param rutaArchivo La ruta donde esta laojado el pdf con el objectivo de adjuntarlo
     * @return true si se formo el emial correctamente
     */
    public boolean FormarEmail(String destinatario, String rutaArchivo) {

        String usuario = "proyectopython18@gmail.com";
        String contraseña = "Python2018.";

        Properties propiedades = new Properties();

        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(propiedades, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });

        Message msg = new MimeMessage(sesion);

        try {
            msg.setFrom(new InternetAddress("PlanEstudiosReportes@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            //El asunto del email
            msg.setSubject("Reporte de cursos del plan de estudios seleccionado");

            Multipart contenidoEmail = new MimeMultipart();
            //Cuerpo del email
            MimeBodyPart texto = new MimeBodyPart();
            texto.setText("Buenas, se le adjunta el archivo con el plan de estudios seleccionados y todos sus cursos");
            texto.setText("");

            //Adjuntar archivo
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(rutaArchivo);

            //Se unen las partes
            contenidoEmail.addBodyPart(texto);
            contenidoEmail.addBodyPart(adjunto);

            //se agrega al contenido
            msg.setContent(contenidoEmail);

            Transport.send(msg);

        } catch (MessagingException e) {
            System.out.println("No se pudo enviar el correo");
            return false;
        } catch (IOException e) {
            System.out.println("Error con el archivo");
            return false;
        }
        return true;
    }

    /**
     * Metodo el cual va a envier el correo formalmente
     * @param destinatario El usurio al que le va llegar al correo
     */
    public void EnviarEmail(String destinatario) {
        try {
            String ruta = crearPDF();
            FormarEmail(destinatario, ruta);

            JOptionPane.showMessageDialog(null, "El email fue enviado correctamente");
        } catch (SQLException e) {
            Logger.getLogger(PlanEstudioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
