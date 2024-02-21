package learprovim;

import java.sql.*;
import java.sql.SQLException;

public class ConexionBD {

    private String url;
    private String usuario;
    private String contrasena;

    public ConexionBD(String url, String usuario, String contrasena) {
        this.url = url;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Connection establecerConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    public void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verificarEstudianteRegistrado(String nombreEstudiante) {
        boolean registrado = false;
        try (Connection conexion = establecerConexion()) {
            String consulta = "SELECT COUNT(*) AS total FROM usuarios WHERE nombre = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                registrado = total > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrado;
    }

    public String obtenerNivelEstudiante(String nombreEstudiante) {
        String nivel = null;
        try (Connection conexion = establecerConexion()) {
            String consulta = "SELECT nivel FROM usuarios WHERE nombre = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nivel = resultSet.getString("nivel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nivel;
    }
}
