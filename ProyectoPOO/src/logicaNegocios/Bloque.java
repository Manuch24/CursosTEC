package logicaNegocios;

import java.util.ArrayList;

/**
 *
 * @author Bryan Berrcoal
 * @author Manuel Chaves
 */
public class Bloque {
    //variables

    private int numBloque;
    private ArrayList<Curso> cursos;

    /**
     * Constructor del modelo del bloque
     * @param numBloque Es el número del bloque
     */
    public Bloque(int numBloque) {
        this.numBloque = numBloque;
        this.cursos = new ArrayList<Curso>();
    }

    public int getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(int numBloque) {
        this.numBloque = numBloque;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Bloque: [" + "numBloque=" + numBloque + "cursos= " + cursos + "]";
    }

    /**
     * Método que añade el curso al arraylist
     * @param curso es el objeto curso que se va agregar al arraylist
     */
    public void agregarCursoBloque(Curso curso) {
        cursos.add(curso);
        System.err.println("curso añadido al bloque: " + getNumBloque());
    }

}
