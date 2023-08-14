package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.BillboardItem;
import es.udc.paproject.backend.model.entities.Pelicula;
import es.udc.paproject.backend.model.entities.Sesion;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSearchDateException;
import es.udc.paproject.backend.model.exceptions.MovieAlreadyStartedException;

import java.time.LocalDate;
import java.util.List;

public interface CarteleraService {
    Sesion buscarSesionPorId(Long idSesion) throws InstanceNotFoundException, MovieAlreadyStartedException;

    List<BillboardItem> buscarSesionesPorDia(LocalDate date) throws InvalidSearchDateException;

    Pelicula buscarPeliculaPorId(Long id) throws InstanceNotFoundException;
}
