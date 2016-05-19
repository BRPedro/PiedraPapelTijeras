/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Melanie
 */
@SuppressWarnings("UseSpecificCatch")
public class BD_conexion {

    private Connection conexion;
    private PreparedStatement sentencia = null;

    public Connection getConexion() {
        return conexion;
    }

    public boolean conectarBD() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url_BD = "jdbc:sqlserver://DESKTOP-38AOKC2\\172.24.45.94:1433;databaseName=Juego";
        conexion = DriverManager.getConnection(url_BD,"sa","1234");
        
        
        /*Properties config = new Properties();
        String archivo = "../Utils/config.properties";
        InputStream is = getClass().getResourceAsStream(archivo);

        if (is != null) {
            config.load(is);
        } else {
            throw new FileNotFoundException("No se encontró el archivo de configuración de la aplicación.");
        }
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(config.getProperty("host"), config.getProperty("user"),
                config.getProperty("pass"));*/
        return conexion != null;
    }        

    /**
     * ***********CONSULTAS Y EJECUCIONES*****************
     */
    /**
     * **********************************************************
     * Cada elemento del Array de parametros debe enviarse en el siguiente
     * formato: "valor|Numtipo" Ejemplo: 200|0
     *
     * Los tipos corresponden a los siguientes numeros Number : 0 String : 1
     * Date: 2 Double: 3 
     *
     * Se pueden agregar dependiendo de la necesidad.
     *
     * ************************************************************* @param sql
     * @param sql
     * @param params
     * @return
     * @throws java.sql.SQLException
     */
    public boolean ejecutar(String sql, String[] params) throws SQLException {
        sentencia = conexion.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                String paramsDatos[] = params[i].split("\\|");
                String valorDato = paramsDatos[0];
                int tipoDato = Integer.parseInt(paramsDatos[1]);
                switch (tipoDato) {
                    case 0:
                        sentencia.setInt(i + 1, Integer.parseInt(valorDato));
                        break;
                    case 1:
                        sentencia.setString(i + 1, valorDato);
                        break;
                    case 2:
                        java.sql.Date date = java.sql.Date.valueOf(valorDato);
                        sentencia.setDate(i + 1, date);
                        break;
                    case 3:
                        sentencia.setDouble(i + 1, Double.parseDouble(valorDato));
                        break;
                }
            }
        }
        sentencia.executeUpdate();
        return true;
    }

    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "UseSpecificCatch"})
    public ResultSet consultar(String sql, Object params[]) throws SQLException {
        ResultSet resultado;
        sentencia = conexion.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                String d = (String) params[i];
                String paramsDatos[] = d.split("\\|");
                String valorDato = paramsDatos[0];
                int tipoDato = Integer.parseInt(paramsDatos[1]);
                switch (tipoDato) {
                    case 0:
                        sentencia.setInt(i + 1, Integer.parseInt(valorDato));
                        break;
                    case 1:
                        sentencia.setString(i + 1, valorDato);
                        break;
                    case 2:
                        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
                        java.util.Date fecha;
                        try {
                            fecha = formatter.parse(valorDato);
                            Date sqlDate = new Date(fecha.getTime());
                            sentencia.setDate(i + 1, sqlDate);
                            break;
                        } catch (ParseException ex) {
                            return null;
                        }
                }
            }
        }
        resultado = sentencia.executeQuery();
        return resultado;
    }

    public void flush() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                this.conexion.close();
            }
            if (sentencia != null) {
                this.sentencia.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error cerrando cursores o conexion");
        }
    }

    boolean ejecutar(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}