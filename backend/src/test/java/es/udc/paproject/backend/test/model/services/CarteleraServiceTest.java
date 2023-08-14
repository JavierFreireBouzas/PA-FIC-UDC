package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSearchDateException;
import es.udc.paproject.backend.model.exceptions.MovieAlreadyStartedException;
import es.udc.paproject.backend.model.services.CarteleraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CarteleraServiceTest {
    @Autowired
    private PeliculaDao peliculaDao;
    @Autowired
    private SesionDao sesionDao;
    @Autowired
    private SalaDao salaDao;
    @Autowired
    private CarteleraService carteleraService;

    private final Long ID_INEXISTENTE = Long.valueOf(-1);

    private Pelicula addPelicula(String titulo, String resumen, int duracion){
        return peliculaDao.save(new Pelicula(titulo, resumen, duracion));
    }
    private Sesion addSesion(Pelicula pelicula, Sala sala, LocalDateTime hora, BigDecimal precio, int localidadesLibres){
        return sesionDao.save(new Sesion(pelicula, sala, hora, precio, localidadesLibres));
    }

    private Sala addSala(String nombreSala, int capacidad){
        return salaDao.save(new Sala(capacidad, nombreSala));
    }

    @Test
    public void obtenerPeliculasySesionesfromId() throws InstanceNotFoundException, MovieAlreadyStartedException {
        Pelicula pelicula = addPelicula("Ratatouille",
                "Las peripecias de una rata que se adentra en el mundo de la cocina en la ciudad de Paris", 111);

        Sala sala1 = addSala("sala1", 100);
        Sesion sesion1 = addSesion(pelicula, sala1, LocalDateTime.now().plusHours(4), BigDecimal.valueOf(6),100);


        Pelicula film =  carteleraService.buscarPeliculaPorId(pelicula.getId());
        assertEquals(pelicula.getTitulo(), film.getTitulo());
        assertEquals(pelicula.getDuracion(), film.getDuracion());
        assertEquals(pelicula.getResumen(), film.getResumen());



        Sesion findSesion = carteleraService.buscarSesionPorId(sesion1.getId());
        assertEquals(findSesion.getSala().getNombreSala(), sala1.getNombreSala());
        assertEquals(findSesion.getPelicula().getTitulo(), pelicula.getTitulo());
        assertEquals(findSesion.getPrecio(), sesion1.getPrecio());
    }
    @Test
    public void buscarPeliculaInexistente(){
        assertThrows(InstanceNotFoundException.class,	() ->
                carteleraService.buscarPeliculaPorId(ID_INEXISTENTE));
    }
    @Test
    public void buscarSesionInexistente(){
        assertThrows(InstanceNotFoundException.class,	() ->
                carteleraService.buscarSesionPorId(ID_INEXISTENTE));
    }

    @Test
    public void obtenerSesionPasada(){
        Pelicula pelicula = addPelicula("Ratatouille",
                "Las peripecias de una rata que se adentra en el mundo de la cocina en la ciudad de Paris", 111);
        Sala sala1 = addSala("sala1", 100);
        Sesion sesion1 = addSesion(pelicula, sala1, LocalDateTime.now().minusHours(4), BigDecimal.valueOf(6),100);
        assertThrows(MovieAlreadyStartedException.class,	() ->
                carteleraService.buscarSesionPorId(sesion1.getId()));

    }

    @Test
    public void testBuscarSesionesPorFecha() throws InvalidSearchDateException {

        LocalDateTime date = LocalDate.now().atTime(23, 0);
        Sala sala1 = addSala("sala1", 100);

        // Se crean películas desordenadas alfabéticamente
        Pelicula pelicula2 = addPelicula("BBBB",
                "Resumen", 111);
        Pelicula pelicula1 = addPelicula("AAAA",
                "Resumen", 111);

        // Se añaden sesiones desordenadas por película y fecha
        Sesion sesion2 = addSesion(pelicula1, sala1,
                date.plusMinutes(30), BigDecimal.valueOf(6),100);
        Sesion sesion1 = addSesion(pelicula1, sala1,
                date, BigDecimal.valueOf(6),100);

        Sesion sesion4 = addSesion(pelicula2, sala1,
                date.plusDays(1), BigDecimal.valueOf(6),100);
        Sesion sesion3 = addSesion(pelicula2, sala1,
                date, BigDecimal.valueOf(6),100);

        // Se crean los Billboards que se espera que se devuelvan en el orden correspondiente
        BillboardItem item1 = new BillboardItem(pelicula1, List.of(sesion1, sesion2));
        BillboardItem item2 = new BillboardItem(pelicula2, List.of(sesion3));

        // Se añaden los Billboards a la lista esperada en el orden correspondiente
        List<BillboardItem> expectedList = new ArrayList<>
                (Arrays.asList(item1, item2));

        // Se comprueba que la lista devuelta es igual a la esperada
        assertEquals(expectedList, carteleraService.buscarSesionesPorDia(LocalDate.now()));
    }

    @Test
    public void testBuscarSesionesPorFechaNoCoincidente() throws InvalidSearchDateException {

        LocalDateTime date = LocalDate.now().atStartOfDay().plusDays(2);
        Sala sala1 = addSala("sala1", 100);
        Pelicula pelicula = addPelicula("Película", "Resumen", 111);

        // Se añaden sesiones con fecha distinta a la de la búsqueda
        addSesion(pelicula, sala1, date, BigDecimal.valueOf(6),100);
        addSesion(pelicula, sala1, date, BigDecimal.valueOf(6),100);

        List<BillboardItem> emptyList = new ArrayList<>();

        // Se comprueba que la lista devuelta está vacía
        assertEquals(emptyList, carteleraService.buscarSesionesPorDia(LocalDate.now()));
    }

    @Test
    public void testBuscarSesionesInvalidDate(){
        assertThrows(InvalidSearchDateException.class, () ->
                carteleraService.buscarSesionesPorDia(LocalDate.now().plusDays(10)));
        assertThrows(InvalidSearchDateException.class, () ->
                carteleraService.buscarSesionesPorDia(LocalDate.now().minusDays(2)));
    }
}
