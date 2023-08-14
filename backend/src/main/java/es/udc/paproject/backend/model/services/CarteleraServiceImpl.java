package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSearchDateException;
import es.udc.paproject.backend.model.exceptions.MovieAlreadyStartedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CarteleraServiceImpl implements CarteleraService{

    @Autowired
    private SesionDao sesionDao;
    @Autowired
    private PeliculaDao peliculaDao;



    @Override
    public Sesion buscarSesionPorId(Long idSesion) throws InstanceNotFoundException, MovieAlreadyStartedException {
        Optional<Sesion> sesion = sesionDao.findById(idSesion);

        if(sesion.isEmpty()){
            throw new InstanceNotFoundException("project.entities.sesion", idSesion);
        }
        if(sesion.get().getFecha().isBefore(LocalDateTime.now())){
            throw new MovieAlreadyStartedException(sesion.get().getId(), sesion.get().getPelicula().getId());
        }
        return sesion.get();

    }

    @Override
    public List<BillboardItem> buscarSesionesPorDia(LocalDate date) throws InvalidSearchDateException {

        if(date.isBefore(LocalDate.now())){
            throw new InvalidSearchDateException(date);
        }

        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1).minusMinutes(1);

        if (date.isAfter(LocalDate.now().plusDays(6))) {
            throw new InvalidSearchDateException(date);
        }

        // Se recuperan las sesiones de un día
        List<Sesion> sesions =
                sesionDao.findByFechaBetweenOrderByPeliculaIdAscFechaAsc(startDate, endDate);
        List<BillboardItem> items = new ArrayList<>();

        // Se recorre la lista de sesiones para clasificarlas por película
        for (Sesion sesion : sesions) {
            boolean peliculaEncontrada = false;
            if (!sesion.getFecha().isBefore(LocalDateTime.now())) {
                for (BillboardItem item : items) {
                    if (item.getPelicula().getId().equals(sesion.getPelicula().getId())) {
                        peliculaEncontrada = true;
                        item.getSesions().add(sesion);
                        break;
                    }
                }
                if (!peliculaEncontrada) {
                    BillboardItem nuevoItem = new BillboardItem(sesion.getPelicula(), new ArrayList<>(List.of(sesion)));
                    items.add(nuevoItem);
                }
            }
        }

        // Se ordena la lista por título de películas
        items.sort(Comparator.comparing(item -> item.getPelicula().getTitulo()));

        return items;

    }

    @Override
    public Pelicula buscarPeliculaPorId(Long idPelicula) throws InstanceNotFoundException {
        Optional<Pelicula> pelicula = peliculaDao.findById(idPelicula);
        if(pelicula.isEmpty()){
            throw new InstanceNotFoundException("project.entities.pelicula", idPelicula);
        }
        return pelicula.get();
    }
}
