
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    public static final String URL = "jdbc:mysql://localhost:3306/basereserva";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static Connection conectar(){
    Connection connection = null;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
    } catch(Exception e){
        System.out.println(e);
    }
        return connection;
    }
}
