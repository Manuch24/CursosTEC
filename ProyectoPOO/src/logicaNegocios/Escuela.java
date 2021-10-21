package logicaNegocios;

import java.util.ArrayList;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class Escuela {

    private String codigo;
    private String nombre;
    private ArrayList<PlanEstudio> planes;

    /**
     * Contructor del modelo escuela
     *
     * @param pCodigo el codigo de la escuela
     * @param pNombre El nombre de la escuela
     */
    public Escuela(String pCodigo, String pNombre) {
        this.codigo = pCodigo;
        this.nombre = pNombre;
        this.planes = new ArrayList<PlanEstudio>();
    }

    /**
     * constructor vacio
     */
    public Escuela() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public ArrayList<PlanEstudio> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<PlanEstudio> planes) {
        this.planes = planes;
    }

    @Override
    public String toString() {
        return "Escuela{" + "codigo=" + codigo + ", nombre=" + nombre + ", planes=" + planes + '}';
    }

}
