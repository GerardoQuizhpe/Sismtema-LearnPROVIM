
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
        AgentController agentePerfilUsuario = container.createNewAgent("AgentePerfilUsuario", "learprovim.AgentePerfilUsuario", new Object[]{});
        agentePerfilUsuario.start();
        
        AgentController agenteRecomendacion  = container.createNewAgent("AgenteRecomendacion", "learprovim.AgenteRecomendacion", new Object[]{});
        agenteRecomendacion.start();
    }
    
//    private static void iniciarAgentes(ContainerController containerController) {
//        try {
//            AgentController agentePerfilUsuario = containerController.createNewAgent(
//                    "AgentePerfilUsuario",
//                    "learprovim.AgentePerfilUsuario",
//                    new Object[]{});
//            agentePerfilUsuario.start();
//
//            AgentController agenteRecomendacion = containerController.createNewAgent(
//                    "AgenteRecomendacion",
//                    "learprovim.AgenteRecomendacion",
//                    new Object[]{});
//            agenteRecomendacion.start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}