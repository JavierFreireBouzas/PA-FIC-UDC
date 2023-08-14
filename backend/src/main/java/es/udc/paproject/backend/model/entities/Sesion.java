package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Sesion {

    private Long id;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime fecha;
    private BigDecimal precio;
    private int localidadesLibres;
    private long version;

    public Sesion() {}

    public Sesion(Pelicula pelicula, Sala sala, LocalDateTime fecha, BigDecimal precio, int localidadesLibres) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.precio = precio;
        this.localidadesLibres = localidadesLibres;
    }

    public Sesion(Pelicula pelicula, Sala sala, long version, LocalDateTime fecha, BigDecimal precio, int localidadesLibres) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.precio = precio;
        this.localidadesLibres = localidadesLibres;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "peliculaId")
    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "salaId")
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getLocalidadesLibres() {
        return localidadesLibres;
    }

    public void setLocalidadesLibres(int localidadesLibres) {
        this.localidadesLibres = localidadesLibres;
    }


    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
