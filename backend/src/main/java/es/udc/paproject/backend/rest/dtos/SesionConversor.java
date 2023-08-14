package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Sesion;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SesionConversor {
    private SesionConversor(){}

    public static SesionDto toSesionDto(Sesion sesion){
        return new SesionDto(sesion.getId(), sesion.getPrecio(), toMillis(sesion.getFecha()), sesion.getLocalidadesLibres(),sesion.getPelicula().getDuracion(),sesion.getSala().getNombreSala(),
                sesion.getPelicula().getId(),sesion.getPelicula().getTitulo());
    }


    private static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
