package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Pelicula;
import org.springframework.security.core.parameters.P;

public class PeliculaConversor {

    private PeliculaConversor() {}
    public static PeliculaDto toPeliculaDto(Pelicula pelicula){
        return new PeliculaDto(pelicula.getId(), pelicula.getTitulo(),pelicula.getResumen(), pelicula.getDuracion());
    }
}
