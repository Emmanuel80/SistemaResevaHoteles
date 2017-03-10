
package Logica;

import Datos.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fHabitacion {
    private conexion mysql= new conexion();
    private Connection con= mysql.conectar();
    private String sSql="";
    public Integer totalRegistros;
    PreparedStatement prep;
    
    public DefaultTableModel mostrar(String Buscar){
        DefaultTableModel model;
        String titulos[]={"Id","Numero","Piso","Descripcion","Caracteristicas","Precio","Estado","Tipo/Habiacion"};
        String Registros[]= new String[8];
        
        totalRegistros=0;
        model= new DefaultTableModel(null,titulos);
        sSql="SELECT *FROM habitacion WHERE CONCAT (hab_numero,' ',hab_piso) LIKE'%"+Buscar+"%'order by pk_hab_id";
        try {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(sSql);
            while(rs.next()){
                Registros[0]=rs.getString("pk_hab_id");
                Registros[1]=rs.getString("hab_numero");
                Registros[2]=rs.getString("hab_piso");
                Registros[3]=rs.getString("hab_descripcion");
                Registros[4]=rs.getString("hab_caracteristicas");
                Registros[5]=rs.getString("hab_precio");
                Registros[6]=rs.getString("hab_estado");
                Registros[7]=rs.getString("hab_tipo");
                
                totalRegistros=totalRegistros+1;
                model.addRow(Registros);
                
            
            }
            return model;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean insertar (Habitacion dts){
        try {
            sSql="insert into habitacion(hab_numero,hab_piso,hab_descripcion,hab_caracteristicas,hab_precio,hab_estado,hab_tipo) Values(?,?,?,?,?,?,?,?)";
            prep=con.prepareStatement(sSql);
            prep.setString(1, dts.getNumero());
            prep.setString(2,dts.getPiso());
            prep.setString(3, dts.getDescripcion());
            prep.setString(4, dts.getCaracteristicas());
            prep.setString(5, dts.getPrecio_diario());
            prep.setString(6, dts.getEstado());
            prep.setString(7, dts.getTipo_habitacion());
           
            int n=prep.executeUpdate();
            if(n!=0){
               return true;
            }else{
                return false;
            }
            
      
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
    
    }
    
        public boolean Editar (Habitacion dts){
        try {
            sSql="update habitacion set hab_numero=?,hab_piso=?,hab_descripcion=?,hab_caracteristicas=?,hab_precio=?,hab_estado=?,hab_tipo=? where pk_hab_id=?";
            prep=con.prepareStatement(sSql);
            prep.setString(1,dts.getNumero());
            prep.setString(2,dts.getPiso());
            prep.setString(3, dts.getDescripcion());
            prep.setString(4, dts.getCaracteristicas());
            prep.setString(5, dts.getPrecio_diario());
            prep.setString(6, dts.getEstado());
            prep.setString(7, dts.getTipo_habitacion());
            prep.setInt(8,dts.getIdhabitacion());
            
            int n= prep.executeUpdate();
            
            if(n!=0){
                    return true;
        }else{
            return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
    
    }
        
            public boolean Eliminar (Habitacion dts){
        try {
            sSql="delete from habitacion where pk_hab_id=?";
            prep=con.prepareStatement(sSql);
            prep.setInt(1, dts.getIdhabitacion());
            
            int n= prep.executeUpdate();
            if(n!=0){
                return true;
        }else{
            return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
    
    }
    
}
