/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * CLase que define los servicios restful que va a usar nuestra aplicación.
 *
 * @author Cristina Milea
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    /**
     * Método que crea la colección donde se van a ir añadiendo cada uno de los
     * restful.
     *
     * @return los recursos que se usan.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(restful.AlumnoFacadeREST.class);
        resources.add(restful.BibliotecarioFacadeREST.class);
        resources.add(restful.GrupoFacadeREST.class);
        resources.add(restful.LibroFacadeREST.class);
        resources.add(restful.ProfesorFacadeREST.class);
        resources.add(restful.UsuarioFacadeREST.class);
    }

}
