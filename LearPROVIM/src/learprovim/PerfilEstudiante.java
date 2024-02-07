package learprovim;

import java.io.Serializable;

public class PerfilEstudiante implements Serializable {

    private String nombre;
    private int ciclo;
    private String materia;

    public PerfilEstudiante(String nombre, int ciclo, String materia) {
        this.nombre = nombre;
        this.ciclo = ciclo;
        this.materia = materia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCiclo() {
        return ciclo;
    }

    public String getMateria() {
        return materia;
    }

}

