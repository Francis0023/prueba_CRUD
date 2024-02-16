//import javax.swing.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class insertarDatos {
//    // Establecer los parametros
//    String nombre;
//    int salario;
//    int celular;
//
//
//    // Constructor
//
//
//    public insertarDatos(String nombre, int salario, int celular) {
//        this.nombre = nombre;
//        this.salario = salario;
//        this.celular = celular;
//    }
//    //
//    public insertarDatos(){
//
//    }
//
//
//    // Otro metodo personalizado para probar la conexion a la base de datos
//    public void ingresoDatos(String nombre,int salario,int celular) {
//        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/compania","root","f123456")){
//            // Formato para insertar los valores
//            String sql="INSERT INTO empleados(nombre,salario,celular)VALUES(?,?,?)";
//            //Para verificar si no estar repetidos los datos
//            try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
//                // Setear los valores
//                preparedStatement.setString(1,nombre);
//                preparedStatement.setString(2, String.valueOf(salario));
//                preparedStatement.setString(3, String.valueOf(celular));
//                int filasAfectadas=preparedStatement.executeUpdate();
//                JOptionPane.showMessageDialog(null,"Se ha guardado con exito!!");
//                if(filasAfectadas>0){
//                    System.out.println("Inserccion exitosa");
//
//                }else{
//                    System.out.println("Inserto de datos fallido");
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
//}