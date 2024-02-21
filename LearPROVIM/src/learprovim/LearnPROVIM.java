
package learprovim;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;

public class LearnPROVIM {

    public static void main(String[] args) throws StaleProxyException {
        // Inicializar contenedor principal
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        ContainerController container = runtime.createMainContainer(profile);

        // Iniciar agentes
        AgentController agentePerfilUsuario = container.createNewAgent("AgentePerfilUsuario", "learnprovim.AgentePerfilUsuario", new Object[]{});
        agentePerfilUsuario.start();
        
        AgentController agenteRecomendacion  = container.createNewAgent("AgenteRecomendacionBasadaContenido", "learnprovim.AgenteRecomendacionBasadaContenido", new Object[]{});
        agenteRecomendacion.start();
    }
}