package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa a la ineterfaz del componente
 * @author Luis Arango
 */
public class ComponentRepository implements IComponentRepository{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Atributo para hacer la conexión con la base de datos
     */
    public Connection conn;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos de conexion">
    /**
     * Método encargado de realizar la conexión a la base de datos
     * @return 1 si la conexión fue exitosa, -1 de lo contrario
     */
    public int connect() {
        try {
            Class.forName(Utilities.loadProperty("server.db.driver"));
            //crea una instancia de la controlador de la base de datos
            String url = Utilities.loadProperty("server.db.url");
            String username = Utilities.loadProperty("server.db.username");
            String pwd = Utilities.loadProperty("server.db.password");
            conn = DriverManager.getConnection(url, username, pwd);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al consultar la tabla Dish de la base de datos", ex);
        }
        return -1;
    }
    
    /**
     * Metodo encargado de desconectar la aplicacion de la base de datos.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }
//</editor-fold>
    /**
     * Crea un componente con un ocjto de tipo componente porporcionado
     * @param prmObjComponente nuevo Objeto componente a ser creafo e insertado en la base de datos
     * @return retorna ID del componente o una excepción en caso de fallar
     */
    //<editor-fold defaultstate="collapsed" desc="Métodos sobre-escritos">
    @Override
    public String createComponente(Component prmObjComponente) {
        try {
            this.connect();
            String sql = "INSERT INTO Component (compID, compName, compType, compPrice, compImage) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjComponente.getCompId());
            pstmt.setString(2, prmObjComponente.getCompName());
            pstmt.setString(3, prmObjComponente.getCompType());
            pstmt.setInt(4, prmObjComponente.getCompPrice());
            pstmt.setBytes(5, prmObjComponente.getCompImage());
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al insertar el ObjComponente", ex);
        }
        return Integer.toString(prmObjComponente.getCompId());
    }
    
    @Override
    public Component findComponente(int prmcompID) {
        //TODO:Implementar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String deleteComponente(int prmcompID) {
        //TODO:Implementar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String updateComponente(int prmcompID) {
        //TODO:Implementar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>
}
