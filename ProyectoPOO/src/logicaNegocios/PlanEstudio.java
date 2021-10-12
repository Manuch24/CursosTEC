/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
* @author Bryan Berrocal
 * @author Manuel Chaves * 
 */
public class PlanEstudio {
  private int numPlan; 
  private LocalDate vigencia; 
  private ArrayList<Bloque> bloques;

  public PlanEstudio(int numPlan, LocalDate vigencia) {
    this.numPlan = numPlan;
    this.vigencia = vigencia;
    this.bloques = new ArrayList<Bloque>();
  }

  public int getNumPlan() {
    return numPlan;
  }

  public void setNumPlan(int numPlan) {
    this.numPlan = numPlan;
  }

  public LocalDate getVigencia() {
    return vigencia;
  }

  public void setVigencia(LocalDate vigencia) {
    this.vigencia = vigencia;
  }

  public ArrayList<Bloque> getBloques() {
    return bloques;
  }

  public void setBloques(ArrayList<Bloque> bloques) {
    this.bloques = bloques;
  }

  @Override
  public String toString() {
    return "PlanEstudio{" + "numPlan=" + numPlan + ", \nvigencia=" + vigencia + ", \nbloques=" + bloques + '}';
  }
  
  
  /***
   * 
   * @param nombre
   * @param codigo
   * @param creditos
   * @param horasLectivas
   * @param numBloque 
   * 
   * @see validarBloque
   */
  public void registrarCursoPlan(String nombre, String codigo, int creditos, int horasLectivas, int numBloque) {
    Curso curso = new Curso(nombre, codigo, creditos, horasLectivas);
    if (existeCursoPlan(curso) == false) {
      if (validarBloque(numBloque) == false) {
        Bloque bloque = new Bloque(numBloque);
        bloque.agregarCursoBloque(curso);
        bloques.add(bloque);
        System.err.println("Blque y curso " + curso.getCodigo() + " registrado al plan: " + getNumPlan() + "\n" + "Al bloque: " + bloque.getNumBloque());
      } else {
        for (Bloque bloque : bloques) {
          if (bloque.getNumBloque() == numBloque) {
            bloque.agregarCursoBloque(curso);
            System.err.println("Curso " + curso.getCodigo() + " registrado al plan: " + getNumPlan() + "\n" + "Al bloque: " + bloque.getNumBloque());
          }
        }
      }
    } else {
      System.err.println("El curso " + curso.getCodigo() + " ya esta en alguno de los bloques del plan perro");
    }
  }
  
  /***
   * 
   * @param numBloque
   * @return true si existe un semestre con ese numero, false si no existe
   */
  public boolean validarBloque(int numBloque){
    for (Bloque bloque: bloques){
      if (bloque.getNumBloque() == numBloque){
        return true;
      }
      }
      return false;
}
  
  public boolean existeCursoPlan(Curso pCurso){
    for (Bloque bloque: bloques){
      //Se crea lista de cursos
      ArrayList<Curso> cursos = new ArrayList<Curso>();
      cursos =bloque.getCursos();
      //validar que de la lista de cursos del bloque no esté el mismo que se vaya a agregar
      for(Curso curso: cursos){
        if (curso.getCodigo() == pCurso.getCodigo()){
          System.err.println("El curso " + pCurso.getNombre() + " ya existe en el plan");
          return true;
        }
      }
    }
      return false;
  }
  
  
}