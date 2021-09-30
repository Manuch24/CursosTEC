/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.util.ArrayList;

/**
 *
 * @author Bryan Berrcoal
 * @author Manuel Chaves
 */
public class Bloque {
  private int numBloque;
  private ArrayList<Curso> cursos;
  // codigo de cual plan pertence???

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
  
  
  
  
}
