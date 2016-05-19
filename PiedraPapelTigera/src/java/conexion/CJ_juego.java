/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import BD.Torneo;
import Control.Arbol;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PedroBR
 */
public class CJ_juego {
    public JsonObject jugada(String texto){
        Arbol arbol=new Arbol();
        arbol = arbol.crearArbol(texto);
        
        JsonObject object = new JsonObject();
        ArrayList<String> resultado = arbol.VerGanador();
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        jsonObject.add("ganador", gson.toJsonTree(resultado));   
        return jsonObject;
    }
    public JsonObject insertat(String p1,String p2) throws SQLException, ClassNotFoundException{
       boolean respuesta = new Torneo().insertarTorneo(p1, p2);
       String res="";
       if(respuesta)res="Exito";
       else res="Fracaso";
       JsonObject json = new JsonObject();

        json.getAsJsonObject(res);

        return json;
    } 
    
}
