package intecap;


import intecap.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Bases_ejemplo {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    
    
    
    public void menu()
    {
        
        while (true) 
        { 
            
            Scanner entrada = new Scanner(System.in);
            System.out.println("===================================");
            System.out.println("I       Menu principal             I");
            System.out.println("I 1.Consultar                      I");
            System.out.println("I 2.Crear                          I");
            System.out.println("I 3.Modificar                      I");
            System.out.println("I 4.Eliminar                       I");
             
            int repuesta = entrada.nextInt();
            
            switch (repuesta)
            {             
            case 1:
                listar();
                        
            break;
                     
                
            case 2:              
                
                 crear(); 
                                
            break;
            
            
            case 3:
                System.out.println("Ingrese Codigo para modificar usuario");
                
                 
                 modificar();
                 System.out.println("se ha modificado correctamente");
            break;
            
            
            case 4:
                 int codigo = entrada.nextInt();                
                 eliminar(codigo);
                 System.out.println("El usuario ha sido elimado del registro");
            break;
           
            case 5:                
            System.exit(0);
            break;
            
            default:
            throw new AssertionError();          
            }           
        } 
    }
    
    public void listar(){
    
        String instruccion = "select * from alumno";
        
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getInt(3));
                System.out.println(rs.getInt(4));
                System.out.println("---------------");
            }
            
        } catch (Exception e) {
        }
    
    }
    
    public void crear(String direccion, String nombre, int numero, int codigo){
    String sql = "insert into alumno(direccion,nombre,numero,Codigo) values (?,?,?,?)";
    
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, direccion);
            ps.setString(2, nombre);
            ps.setInt(3, numero);
            ps.setInt(4, codigo);
            ps.executeUpdate();
            
            
        } catch (Exception e) {
        }
    
    
    }
    
    public void modificar(String direccion, String nombre, int numero, int codigo){
    
    String sql = "update alumnos set nombre='" + nombre + "', telefono='" + numero + "', estado='" + direccion + "' where carnet ='" + codigo + "'";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }
    
    public void eliminar(int codigo){
        String sql = "delete from alumno where Codigo = ?";
        try {
            
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    
    
    }
   
  
    
   
}
