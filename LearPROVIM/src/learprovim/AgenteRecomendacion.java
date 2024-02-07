package learprovim;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteRecomendacion extends Agent {
    private String agentePerfilUsuario = "AgentePerfilUsuario";
    @Override
    protected void setup() {
        // Registra el comportamiento para recibir mensajes
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    // Accede al RecomendacionAgente para obtener el nivel del estudiante
                    String nivelEstudiante = msg.getContent();

                    // Aquí puedes utilizar el nivel del estudiante para generar recomendaciones
                    String recomendacion = generarRecomendacion(nivelEstudiante);

                    // Envia la recomendación al estudiante
                    enviarRecomendacion(recomendacion);
                } else {
                    block();
                }
            }
        });
    }

    private String generarRecomendacion(String nivelEstudiante) {
        switch (nivelEstudiante) {
                case "Basico":
                    return "Se encuentra en un nivel : "+ nivelEstudiante + "\nExplora estos recursos:\n" +
                    "1. Aprender a programar: los conceptos básicos: https://www.ionos.es/digitalguide/paginas-web/desarrollo-web/aprender-a-programar-introduccion-y-conceptos-basicos/\n" +
                    "2. Curso de programación desde cero | Principio básico de programación #1: https://www.youtube.com/watch?v=AEiRa5xZaZw";
                case "Medio":
                    return "Se encuentra en un nivel : "+ nivelEstudiante + " \nExplora estos recursos:\n" +
                    "1. Ejemplos de Programación Orientada a Objetos: https://ejemplo.com.ar/programacion-orientada-a-objetos-ejemplos/\n" +
                    "2. Programación Orientada a Objetos explicada: https://www.youtube.com/watch?v=Nka4JSBgf7I";
                case "Avanzado":
                    return "Se encuentra en un nivel : "+ nivelEstudiante + "\nSumérgete en desafíos avanzados:\n" +
                    "1. Capítulo 4. Programación avanzada: https://uniwebsidad.com/libros/javascript/capitulo-4\n" +
                    "2. Programación Movil: https://www.youtube.com/watch?v=-pWSQYpkkjk";
                default:
                    return "Nivel no reconocido.";
            }
    }

    private void enviarRecomendacion(String contenido) {
        // Envía la recomendación al estudiante (puedes ajustar los destinatarios según tu sistema)
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(contenido);
        msg.addReceiver(getAID(agentePerfilUsuario));
        send(msg);
    }
}
