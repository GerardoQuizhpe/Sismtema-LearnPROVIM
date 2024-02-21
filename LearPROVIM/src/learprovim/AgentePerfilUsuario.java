package learprovim;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import javax.swing.JOptionPane;
import java.sql.*;

public class AgentePerfilUsuario extends Agent {

    private ConexionBD conexionBD;
    private String agenteRecomendacionBasadaContenido = "AgenteRecomendacionBasadaContenido";

    @Override
    protected void setup() {
        // Inicializar la conexión a la base de datos
        conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/estudiantes", "root", "");
        // Obtener el nombre del estudiante
        String nombreEstudiante = obtenerNombreEstudiante();
        if (conexionBD.verificarEstudianteRegistrado(nombreEstudiante)) {
            JOptionPane.showMessageDialog(null, "Bienvenido/a: "+nombreEstudiante);
            // Registra el comportamiento para recibir mensajes
            addBehaviour(new CyclicBehaviour(this) {
                public void action() {
                    ACLMessage msg = receive();
                    if (msg != null) {
                        // Maneja el mensaje recibido
                        if (msg.getPerformative() == ACLMessage.INFORM) {
                            // Muestra la recomendación en la interfaz de usuario
                            mostrarRecomendacion(msg.getContent());
                        }
                    } else {
                        block();
                    }
                }
            });
            String nivelEstudiante = conexionBD.obtenerNivelEstudiante(nombreEstudiante);

            // Envía el nivel al AgenteRecomendacionBasadaComponentes
            enviarNivelAlRecomendacionBasadaComponentes(nivelEstudiante);
        } else {
            // Si el estudiante no está registrado, mostrar un mensaje y finalizar el agente
            JOptionPane.showMessageDialog(null, "El estudiante no está registrado en la base de datos");
            doDelete(); // Termina el agente
        }
    }

    private String obtenerNombreEstudiante() {
        return JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
    }

    private void enviarNivelAlRecomendacionBasadaComponentes(String nivel) {
        // Envía el nivel al AgenteRecomendacionBasadaComponentes
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(nivel);
        msg.addReceiver(getAID(agenteRecomendacionBasadaContenido));
        send(msg);
    }

    // Método para mostrar la recomendación en la interfaz de usuario mediante JOptionPane
    public void mostrarRecomendacion(String recomendacion) {
        JOptionPane.showMessageDialog(null, recomendacion);
    }
}
