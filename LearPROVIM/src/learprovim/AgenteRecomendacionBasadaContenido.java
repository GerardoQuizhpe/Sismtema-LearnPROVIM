package learprovim;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgenteRecomendacionBasadaContenido extends Agent {

    private ConexionBD conexionBD;
    private String agentePerfilUsuario = "AgentePerfilUsuario";

    @Override
    protected void setup() {
        // Inicializar la conexión a la base de datos
        conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/estudiantes", "root", "");

        // Registra el comportamiento para recibir mensajes
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    // Obtener el nivel del estudiante desde la base de datos
                    String nivelEstudiante = msg.getContent();
                    
                    // Obtener las recomendaciones desde la base de datos
                    Map<String, List<String>> recomendaciones = obtenerRecomendaciones(nivelEstudiante);
                    // Calcular la similitud de coseno entre las recomendaciones y el nivel del estudiante
                    Map<String, Double> similitudes = new HashMap<>();
                    for (Map.Entry<String, List<String>> entry : recomendaciones.entrySet()) {
                        double similitud = calcularSimilitudCoseno(entry.getValue(), nivelEstudiante);
                        similitudes.put(entry.getKey(), similitud);
                    }
                    // Construir el mensaje de recomendaciones
                    StringBuilder mensajeRecomendaciones = new StringBuilder();
                    if (!similitudes.isEmpty()) {
                        mensajeRecomendaciones.append("Se encuentra en un nivel: ").append(nivelEstudiante).append("\n");
                        mensajeRecomendaciones.append("Explora estos recursos:\n");
                        for (Map.Entry<String, Double> entry : similitudes.entrySet()) {
                            List<String> recomendacionesParaNivel = recomendaciones.get(entry.getKey());
                            if (recomendacionesParaNivel != null) {
                                for (String recomendacion : recomendacionesParaNivel) {
                                    mensajeRecomendaciones.append("- ").append(entry.getKey()).append(": ").append(recomendacion).append("\n");
                                }
                            }
                        }
                        enviarMensaje(agentePerfilUsuario, mensajeRecomendaciones.toString());
                    }
                    // Enviar el mensaje de recomendaciones al AgentePerfilUsuario
                    enviarMensaje(agentePerfilUsuario, mensajeRecomendaciones.toString());//msg.setContent(contenido.toString());
                    //}
                } else {
                    block();
                }
            }
        });
    }

    // Método para enviar un mensaje al AgentePerfilUsuario
    private void enviarMensaje(String agenteDestino, String contenido) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(contenido);
        msg.addReceiver(getAID(agenteDestino));
        send(msg);
    }

    // Método para obtener las recomendaciones desde la base de datos
    private Map<String, List<String>> obtenerRecomendaciones(String nivel) {
        Map<String, List<String>> recomendaciones = new HashMap<>();
        try (Connection conexion = conexionBD.establecerConexion()) {
            String consulta = "SELECT nombre, url FROM recomendacion WHERE nivel = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nivel);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String url = resultSet.getString("url");
                String recomendacion = nombre + "\nURL: " + url + "\n";
                recomendaciones.computeIfAbsent(nombre, k -> new ArrayList<>()).add(recomendacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recomendaciones;
    }

    // Método para calcular la similitud de coseno entre las recomendaciones y el nivel del estudiante
    private double calcularSimilitudCoseno(List<String> recomendaciones, String nivelUsuario) {
        // Convertir las recomendaciones y las preferencias del usuario en vectores de términos
        Map<String, Integer> vectorRecomendaciones = new HashMap<>();
        for (String recomendacion : recomendaciones) {
            vectorRecomendaciones.put(recomendacion, 1);
        }

        // Calcular el producto punto de los vectores
        int productoPunto = 0;
        for (Map.Entry<String, Integer> entry : vectorRecomendaciones.entrySet()) {
            productoPunto += entry.getValue() * (nivelUsuario.equals(entry.getKey()) ? 1 : 0);
        }

        // Calcular las magnitudes de los vectores
        double magnitudRecomendaciones = Math.sqrt(vectorRecomendaciones.size());
        // La magnitud del usuario es 1 si tiene un nivel, 0 si no tiene
        double magnitudUsuario = nivelUsuario == null ? 0 : 1;

        // Calcular la similitud de coseno
        return productoPunto / (magnitudRecomendaciones * magnitudUsuario);
    }

}
