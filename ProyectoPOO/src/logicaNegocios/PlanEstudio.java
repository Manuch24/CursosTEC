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
  
  
    
}
