/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.SQLException;

/**
 *
 * @author PedroBR
 */
public class Torneo {
    private final BD_conexion db = new BD_conexion();
    
    public boolean insertarTorneo(String jugador1,String jugador2) throws SQLException, ClassNotFoundException {
        db.conectarBD();
        String sql = "exec insercionTorneo "
                + "\'"+jugador1+"\', \'"+jugador1+"\'";
        boolean resultado = db.ejecutar(sql);
        db.flush();
        return resultado;
    }
    
}
