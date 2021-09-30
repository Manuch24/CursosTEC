/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocios;

import java.util.ArrayList;

/**
 *
*  @author Bryan Berrocal
 * @author Manuel Chaves
 */
public class Escuela {
  private String codigo;
  private String nombre;
  private ArrayList<PlanEstudio> planes;
  
  public Escuela(String pCodigo, String pNombre){
    this.codigo = pCodigo;
    this.nombre = pNombre;
    this.planes = new ArrayList <PlanEstudio>();         
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
