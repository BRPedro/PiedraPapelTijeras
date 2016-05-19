/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.ArrayList;

/**
 *
 * @author PedroBR
 */
public class Arbol {

    private String texto;
    private String nombre = "";
    private String juego = "";
    private Arbol hijo1;
    private Arbol hijo2;

    public Arbol() {
    }
    /**
     * Método de que llama el método recursivo, crearArbol(boolean iteracion)
     * @param texto: String
     * @return: Arbol
     */
    public Arbol crearArbol(String texto) {
        this.texto = texto;

        return crearArbol(true);
    }
    /**
     * Método de que crea el arbol
     * @param iteracion: boolean
     * @return: Arbol
     */
    public Arbol crearArbol(boolean iteracion) {
        Arbol arbol = new Arbol();
        int hijos = 0;
        String b;
        for (; texto.length() > 0;) {
            if (texto.substring(0, 1).equals("[")) {
                hijos++;
                texto = texto.substring(1);
                if (!iteracion) {
                    if (hijos == 1) {
                        arbol.setHijo1(crearArbol(false));

                    } else {
                        arbol.setHijo2(crearArbol(false));
                    }
                } else {
                    arbol = (crearArbol(false));
                }
            } else if (texto.substring(0, 1).equals("]")) {
                texto = texto.substring(1);
                return arbol;
            } else if (texto.substring(0, 1).equals("\"")) {
                b = "";
                int comas = 1;
                texto = texto.substring(1);
                for (; texto.length() > 0; texto = texto.substring(1)) {
                    if (texto.substring(0, 1).equals("\"")) {
                        comas++;
                        if (comas % 2 == 0) {
                            texto = texto.substring(1);
                            if (comas <= 2) {
                                arbol.setNombre(b);
                            } else {
                                arbol.setJuego(b);
                            }
                            System.out.println(b);
                            b = "";
                            if (comas == 4) {
                                break;
                            }
                        }
                    }
                    if ((!texto.substring(0, 1).equals(" ")) && (!texto.substring(0, 1).equals(",")) && (!texto.substring(0, 1).equals("\""))) {
                        b += texto.substring(0, 1);
                    }
                }
                return arbol;
            }
            if (texto.length() > 0) {
                texto = texto.substring(1);
            }

        }
        return arbol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public Arbol getHijo1() {
        return hijo1;
    }

    public void setHijo1(Arbol hijo1) {
        this.hijo1 = hijo1;
    }

    public Arbol getHijo2() {
        return hijo2;
    }

    public void setHijo2(Arbol hijo2) {
        this.hijo2 = hijo2;
    }

    public void borrarHijos() {
        this.hijo1 = null;
        this.hijo2 = null;
    }
    /**
     * Método que recorre el árbol verificando el ganador
     * @return: ArrayList<String>
     */
    public ArrayList<String> VerGanador() {
        ArrayList<String> ganador = new ArrayList<String>();
        if (this.hijo1 == null) {
            ganador.add(this.nombre);
            ganador.add(this.juego);
            ganador.add("");
            return ganador;
        } else {
            ArrayList<String> ganador1 = this.hijo1.VerGanador();
            ArrayList<String> ganador2 = this.hijo2.VerGanador();
            if ((ganador1.get(1).equalsIgnoreCase("R") && !ganador2.get(1).equalsIgnoreCase("P")) || (ganador1.get(1).equalsIgnoreCase("P") && !ganador2.get(1).equalsIgnoreCase("S")) || (ganador1.get(1).equalsIgnoreCase("S") && !ganador2.get(1).equalsIgnoreCase("R"))) {
                ganador1.set(2, ganador2.get(0));
                return ganador1;
            } else {
                 ganador2.set(2, ganador1.get(0));
                return ganador2;
            }
        }
    }

}
