/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author l11m06
 */
public class Producto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        String jdbc= "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(jdbc);
            String url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladmin";
            Connection connect =DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            String query = "Select * from Apple";
            ResultSet resultSet = statement.executeQuery(url);
            String format = "|%1$-3d|%2$-17s|%3$-5d\n";
            while(resultSet.next()){
            int idProducto = resultSet.getInt(format);
            String descProd = resultSet.getString("desc_producto");
                int precio = resultSet.getInt("precio");
                System.out.format(format, idProducto, descProd, precio);
            Scanner scan = new Scanner(System.in);
            System.out.println("¿Qué deseas hacer: INSERTAR / BORRAR / CONSULTAR / ACTUALIZAR ?");
                        String accion = scan.nextLine();
            if(accion.equals("INSERTAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingresa el id_producto");
                String idProd = scan.nextLine();
            
              scan = new Scanner(System.in);
                System.out.println("Ingresa el precio");
                precio = scan.nextInt();
                query = "{call insertar_producto(?, ?)}";
                CallableStatement stmt = connect.prepareCall(query);
                stmt.setString(1, descProd);
                stmt.setInt(2, precio);
                stmt.execute();
            scan = new Scanner(System.in);
                System.out.println("Ingresa el desc_producto");
                stmt.setInt(2, precio);
                stmt.execute();
            }
            }
        } catch (Exception e) {        }
            System.err.println(e);
        }
        
        
    }
    
}
