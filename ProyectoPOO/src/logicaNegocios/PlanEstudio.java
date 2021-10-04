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
  
  
  
  public void registrarCursoPlan(String nombre, String codigo, int creditos, int horasLectivas, int numBloque){
        Curso curso = new Curso(nombre, codigo, creditos, horasLectivas);
        //se crea el número de bloque aunque se debería ver si existe primero,cierto...por hacer
        //VALIDAR LISTA BLOQUES
        Bloque bloque = new Bloque(numBloque);
        bloque.agregarCursoBloque(curso);
        //Se añade el bloque a la lista de bloques...igual validar si existe el bloque ya en la lista.. me lleva
        bloques.add(bloque);
        System.err.println("Curso registrado al plan: "+getNumPlan());
        
        
  }
    
  
  
    
}