package learprovim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
    private Connection conexion;

    public ConexionBD(String url, String usuario, String contrasena) {
        try {
            // Establecer conexión a la base de datos
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conectado a Base de datos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificarEstudianteRegistrado(String nombreEstudiante) {
        try {
            String consulta = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreEstudiante);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Devuelve true si el estudiante está registrado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // En caso de error o si el estudiante no está registrado
    }
    
    public String obtenerNivelEstudiante(String nombreEstudiante) {
        try {
            // Realizar la lógica para obtener el nivel del estudiante desde la base de datos
            String consulta = "SELECT materia FROM usuarios WHERE nombre = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreEstudiante);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener la materia asociada al estudiante
                String materia = resultSet.getString("materia");

                // Asignar el nivel según la materia
                switch (materia) {
                    case "Teoría de la programación":
                        return "Basico";
                    case "Programación orientada a objetos":
                        return "Medio";
                    case "Desarrollo basado en plataformas":
                        return "Avanzado";
                    default:
                        return "Nivel no definido";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Nivel no definido";
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
