/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlyFunPackage.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Coconut
 */
public class ConnectionBBDD {
    private static ConnectionBBDD UnicaConexion=null; 
    private Connection Conex;
    private ConnectionBBDD() throws ClassNotFoundException, SQLException{
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://localhost:3306/bdpartidos";
                Conex = DriverManager.getConnection(connectionUrl,"root","root");
    }
    
    public synchronized static ConnectionBBDD GetConexion() throws ClassNotFoundException, SQLException{

       if(UnicaConexion == null){
           UnicaConexion = new ConnectionBBDD();
       }
       return UnicaConexion;
    }
    
    public Connection GetCon(){
           return Conex;
    }
    public void Destroy() throws SQLException{
           Conex.close();
    }
}
