package co.unicauca.microkernel.core.domain;
import co.unicauca.microkernel.common.entities.Component;

/**
 * Interfaz para las utilidades de la entidad Component
 * @author Luis Arango
 */
public interface IComponentRepository {
    /**
     * Crea un nuevo componente
     * @param prmObjComponente objeto a ser creado
     * @return ID del componente creado
     */
    public String createComponente(Component prmObjComponente);
    /**
     * Encuentra un componente por parámetro 
     * @param prmcompID identificador del componente a buscar
     * @return Objeto de tipo Component
     */
    public Component findComponente(int prmcompID);
    /**
     * 
     * @param prmcompID
     * @return 
     */
    public String deleteComponente(int prmcompID);
    public String updateComponente(int prmcompID);
}
