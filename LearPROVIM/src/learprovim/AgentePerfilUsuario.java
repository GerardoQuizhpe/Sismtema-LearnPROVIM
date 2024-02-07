package learprovim;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import javax.swing.JOptionPane;

public class AgentePerfilUsuario extends Agent {

    private ConexionBD conexionBD;
    private String agenteRecomendacion = "AgenteRecomendacion";

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
            //String nombreEstudiante = msg.getContent();
            String nivelEstudiante = conexionBD.obtenerNivelEstudiante(nombreEstudiante);

            // Envía el nivel al RecomendacionAgente
            enviarNivelAlRecomendacion(nivelEstudiante);
        } else {
            // Si el estudiante no está registrado, mostrar un mensaje y finalizar el agente
            JOptionPane.showMessageDialog(null, "El estudiante no está registrado en la base de datos");
            doDelete(); // Termina el agente
        }
    }

    private String obtenerNombreEstudiante() {
        // Puedes optar por obtener el nombre por consola o mediante JOptionPane
        // Aquí presento ambas opciones:

        // Opción 1: Obtener el nombre por consola
        // System.out.print("Ingrese el nombre del estudiante: ");
        // return new Scanner(System.in).nextLine();
        // Opción 2: Obtener el nombre mediante JOptionPane
        return JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
    }

    private void enviarNivelAlRecomendacion(String nivel) {
        // Envía el nivel al RecomendacionAgente (puedes ajustar los destinatarios según tu sistema)
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(nivel);
        msg.addReceiver(getAID(agenteRecomendacion));
        send(msg);
    }

    // Método para mostrar la recomendación en la interfaz de usuario mediante JOptionPane
    public void mostrarRecomendacion(String recomendacion) {
        JOptionPane.showMessageDialog(null, recomendacion);
    }

    protected void takeDown() {
        // Cerrar la conexión a la base de datos al terminar el agente
        if (conexionBD != null) {
            conexionBD.cerrarConexion();
        }
    }
}
