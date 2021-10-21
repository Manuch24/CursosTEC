
package logicaNegocios;

import java.util.ArrayList;

/**
 *
 * @author Bryan Berrocal
 * @author Manuel Chaves
 * 
 */
public class Curso {
  private String nombre;
  private String codigo;
  private int creditos;
  private int horasLectivas;
  private ArrayList<Curso> requisitos;
  private ArrayList<Curso> correquisitos;

    /**
     * Constructor de lal demodelo del curso
     * @param nombre Es el nombre que va recibir el curso
     * @param codigo Es el codigo que va recibir el codigo
     * @param creditos Son la cantidad de creditos que se le asigna al curso
     * @param horasLectivas Son la cantidad de horas que se le asigna al curso
     */
    public Curso(String nombre, String codigo, int creditos, int horasLectivas) {
    this.nombre = nombre;
    this.codigo = codigo;
    this.creditos = creditos;
    this.horasLectivas = horasLectivas;
    this.requisitos = new ArrayList<Curso>();
    this.correquisitos = new ArrayList<Curso>();
  }

    /**
     * COnstructor vacío
     */
    public Curso() {}

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public int getHorasLectivas() {
    return horasLectivas;
  }

  public void setHorasLectivas(int horasLectivas) {
    this.horasLectivas = horasLectivas;
  }

  public ArrayList<Curso> getRequisitos() {
    return requisitos;
  }

  public void setRequisitos(ArrayList<Curso> requisitos) {
    this.requisitos = requisitos;
  }

  public ArrayList<Curso> getCorrequisitos() {
    return correquisitos;
  }

  public void setCorrequisitos(ArrayList<Curso> correquisitos) {
    this.correquisitos = correquisitos;
  }

  @Override
  public String toString() {
    return "Curso[ " + "nombre= " + nombre + "codigo=" + codigo + ", creditos=" + creditos + ", horasLectivas=" + horasLectivas + ", requisitos=" + requisitos + ", correquisitos=" + correquisitos + '}';
  }
  
    /**
     * Método el cual registra los requistos de un curso en el arraylist del curso
     * @param pCurso Es el curso que se anade como requisito
     */
    public void agregarRequisito (Curso pCurso){
    requisitos.add(pCurso);
  }
  
    /**
     * Método el cual registra los correquistos de un curso en el arraylist del curso
     * @param pCurso Es el curso que se anade como correquisito
     */
    public void agregarCorrequisito (Curso pCurso){
    correquisitos.add(pCurso);
  }
  
  
  
}
