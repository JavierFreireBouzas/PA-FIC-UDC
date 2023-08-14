package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SesionDto {


    private Long idSesion;
    private Long idPelicula;
    private String nombrePelicula;
    private int duracion;
    private BigDecimal precio;
    private long fecha;
    private String nombreSala;
    private int localidadesLibres;



    public SesionDto() {}

    public SesionDto(Long idSesion, BigDecimal precio, long fecha, int localidadesLibres, int duracion, String nombreSala, Long idPelicula, String nombrePelicula) {
        this.idSesion = idSesion;
        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.duracion = duracion;
        this.precio = precio;
        this.fecha = fecha;
        this.nombreSala = nombreSala;
        this.localidadesLibres = localidadesLibres;

    }

    public Long getIdSesion() {return idSesion;}

    public void setIdSesion(Long idSesion) {this.idSesion = idSesion;}

    public BigDecimal getPrecio() {return precio;}

    public void setPrecio(BigDecimal precio) {this.precio = precio;}

    public long getFecha() {return fecha;}

    public void setFecha(long fecha) {this.fecha = fecha;}

    public int getLocalidadesLibres() {return localidadesLibres;}

    public void setLocalidadesLibres(int localidadesLibres) {this.localidadesLibres = localidadesLibres;}

    public int getDuracion() {return duracion;}

    public void setDuracion(int duracion) {this.duracion = duracion;}

    public String getNombreSala() {return nombreSala;}

    public void setNombreSala(String nombreSala) {this.nombreSala = nombreSala;}

    public Long getIdPelicula() {return idPelicula;}

    public void setIdPelicula(Long idPelicula) {this.idPelicula = idPelicula;}

    public String getNombrePelicula() {return nombrePelicula;}

    public void setNombrePelicula(String nombrePelicula) {this.nombrePelicula = nombrePelicula;}
}
