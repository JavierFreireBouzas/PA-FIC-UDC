package es.udc.paproject.backend.model.exceptions;

public class SinLocalidadesDisponiblesException extends Exception{

    private String nombreSala;
    private String nombrePelicula;
    private String fechaSesion;

    public SinLocalidadesDisponiblesException(String nombreSala, String nombrePelicula, String fechaSesion){
        this.nombreSala = nombreSala;
        this.nombrePelicula = nombrePelicula;
        this.fechaSesion = fechaSesion;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public String getFechaSesion() {
        return fechaSesion;
    }
}
