package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Compra;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class CompraConversor {

    public final static List<CompraDto> toCompraDtos(List<Compra> compras) {
        return compras.stream().map(o -> toCompraDto(o)).collect(Collectors.toList());
    }

    public final static CompraDto toCompraDto(Compra compra){
        return new CompraDto(toMillis(compra.getFecha()), compra.getId(), compra.getSesion().getPelicula().getTitulo(), compra.getNumLocalidades(), compra.getPrecioTotal(), toMillis(compra.getSesion().getFecha()), compra.isEntregadas());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
