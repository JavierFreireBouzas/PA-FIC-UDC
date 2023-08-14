package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pelicula {

    private Long id;
    private String titulo;
    private String resumen;
    private int duracion;

    public Pelicula() {}

    public Pelicula(String titulo, String resumen, int duracion) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.duracion = duracion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
