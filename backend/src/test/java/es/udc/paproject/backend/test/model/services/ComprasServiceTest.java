package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ComprasService;
import es.udc.paproject.backend.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ComprasServiceTest {

    private final Long ID_INEXISTENTE = Long.valueOf(-1);
    private final String VALID_CREDIT_CARD = "1234567891234567";
    private final String INVALID_CREDIT_CARD = "12345678912345678";
    private final LocalDateTime validSesionDate = LocalDateTime.now().plusHours(1);

    @Autowired
    private UserService userService;

    @Autowired
    private ComprasService comprasService;

    @Autowired
    private CompraDao compraDao;

    @Autowired
    private SesionDao sesionDao;

    @Autowired
    private PeliculaDao peliculaDao;

    @Autowired
    private SalaDao salaDao;

    private User crearCuentaUsuario(String nombreUsuario){

        User usuario = new User(nombreUsuario, "contrasena", "nombre", "apellidos", nombreUsuario + "@" + nombreUsuario + ".com");

        try{
            userService.signUp(usuario);
        }catch(DuplicateInstanceException e){
            throw new RuntimeException(e);
        }

        return usuario;

    }

    private Pelicula addPelicula(String titulo, String resumen, int duracion){
        return peliculaDao.save(new Pelicula(titulo, resumen, duracion));
    }

    private Sala addSala(String nombreSala, int capacidad){
        return salaDao.save(new Sala(capacidad, nombreSala));
    }

    private Sesion addSesion(Pelicula pelicula, Sala sala, LocalDateTime hora, BigDecimal precio, int localidadesLibres){
        return sesionDao.save(new Sesion(pelicula, sala, hora, precio, localidadesLibres));
    }

    private Compra addCompra(User user, Sesion sesion, LocalDateTime hora){
        return compraDao.save(new Compra(user, sesion, hora, 4, "1234 5678 9123 4567", Boolean.FALSE));
    }

    private Compra buscarCompra(Long compraId) throws InstanceNotFoundException {

        Optional<Compra> compra = compraDao.findById(compraId);

        if (!compra.isPresent()) {
            throw new InstanceNotFoundException("project.entities.compra", compraId);
        }

        return compra.get();
    }

    @Test
    public void testRealizarCompraConUsuarioInexistente(){

        Pelicula currentPelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala currentSala = addSala("Sala 1", 20);
        Sesion currentSesion = addSesion(currentPelicula, currentSala, LocalDateTime.of(2023, 10, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 18);

        assertThrows(InstanceNotFoundException.class,	() ->
                comprasService.comprar(ID_INEXISTENTE, currentSesion.getId(), 5, VALID_CREDIT_CARD));

    }

    @Test
    public void testRealizarCompraSesionInexistente(){

        User currentUsuario = crearCuentaUsuario("usuario1");

        assertThrows(InstanceNotFoundException.class,	() ->
                comprasService.comprar(currentUsuario.getId(), ID_INEXISTENTE, 5, VALID_CREDIT_CARD));

    }

    @Test
    public void testRealizarCompraSinSuficientesButacas(){
        User currentUsuario = crearCuentaUsuario("usuario1");
        Pelicula peliculaAsBestas = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala sala2 = addSala("Sala 2", 15);
        Sesion currentSesion = addSesion(peliculaAsBestas, sala2, LocalDateTime.of(2023, 10, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 4);

        assertThrows(SinLocalidadesDisponiblesException.class,	() ->
        comprasService.comprar(currentUsuario.getId(), currentSesion.getId(), 5, "1234567891234567"));

    }

    @Test
    public void testRealizarCompraExitosa() throws InstanceNotFoundException, SinLocalidadesDisponiblesException, MovieAlreadyStartedException {

        User currentUsuario = crearCuentaUsuario("usuario1");
        Pelicula currentPelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala currentSala = addSala("Sala 1", 20);
        Sesion currentSesion = addSesion(currentPelicula, currentSala, LocalDateTime.of(2023, 10, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 18);

        Compra currentCompra = comprasService.comprar(currentUsuario.getId(), currentSesion.getId(), 5, VALID_CREDIT_CARD);
        Compra buscadaCompra = buscarCompra(currentCompra.getId());

        assertEquals(currentCompra, buscadaCompra);
        assertEquals(currentUsuario, currentCompra.getUser());
        assertEquals(currentPelicula, currentCompra.getSesion().getPelicula());
        assertEquals(currentSala, currentCompra.getSesion().getSala());
        assertEquals(currentSesion, currentCompra.getSesion());
        assertEquals(BigDecimal.valueOf(5.0).multiply(BigDecimal.valueOf(5)), currentCompra.getPrecioTotal());

        //probamos que se hayan reducido el número de localidades libres
        assertEquals(currentSesion.getLocalidadesLibres(), 13);
    }

    @Test
    public void testObtenerNoCompras(){

        User currentUsuario = crearCuentaUsuario("usuario1");
        Block<Compra> comprasEsperadas = new Block<>(new ArrayList<>(), false);

        assertEquals(comprasEsperadas, comprasService.encontrarCompras(currentUsuario.getId(), 0, 1));
    }

    @Test
    public void testRealizarComprasObtenerCompras() {

        User currentUsuario = crearCuentaUsuario("usuario1");
        Pelicula peliculaAsBestas = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Pelicula peliculaRatatouille = addPelicula("Ratatouille", "Las peripecias de una rata que se adentra en el mundo de la cocina en la ciudad de Paris", 111);
        Sala sala1 = addSala("Sala 1", 20);
        Sala sala2 = addSala("Sala 2", 15);
        Sesion sesion1 = addSesion(peliculaAsBestas, sala1, LocalDateTime.of(2022, 10, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 18);
        Sesion sesion2 = addSesion(peliculaRatatouille, sala2, LocalDateTime.of(2023, 9, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 18);

        Compra compra1 = addCompra(currentUsuario, sesion1, LocalDateTime.of(2022, 8, 1, 10, 2, 3));
        Compra compra2 = addCompra(currentUsuario, sesion2, LocalDateTime.of(2023, 8, 10, 7, 3, 54));
        Compra compra3 = addCompra(currentUsuario, sesion2, LocalDateTime.of(2023, 8, 25, 11, 4, 45));

        Block<Compra> compraBlock = new Block<>(Arrays.asList(compra3, compra2), true);

        assertEquals(compraBlock, comprasService.encontrarCompras(currentUsuario.getId(), 0, 2));

        compraBlock = new Block<>(Arrays.asList(compra1), false);

        assertEquals(compraBlock, comprasService.encontrarCompras(currentUsuario.getId(), 1, 2));

    }

    @Test
    public void testComprarEntradasPeliculaEmpezada(){
        User currentUsuario = crearCuentaUsuario("usuario1");
        Pelicula currentPelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala currentSala = addSala("Sala 1", 20);
        Sesion currentSesion = addSesion(currentPelicula, currentSala, LocalDateTime.of(2008, 10, 1, 10, 2, 3), BigDecimal.valueOf(5.0), 18);

        assertThrows(MovieAlreadyStartedException.class,	() ->
                comprasService.comprar(currentUsuario.getId(), currentSesion.getId(), 5, "1234 5678 9123 4567"));
    }

    @Test
    public void testEntregarEntradas()
            throws InstanceNotFoundException, SinLocalidadesDisponiblesException,
            MovieAlreadyStartedException, TicketsAlreadyDeliveredException, CreditCardDoesNotMatchException {

        User user = crearCuentaUsuario("usuario");
        Pelicula pelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala sala = addSala("Sala 1", 20);
        Sesion sesion = addSesion(pelicula, sala, validSesionDate, BigDecimal.valueOf(5.0), 18);

        Compra compra = comprasService.comprar(user.getId(), sesion.getId(), 5, VALID_CREDIT_CARD);


        // Se comprueba que las entradas de la compra aún no fueron entregadas
        assertFalse(compra.isEntregadas());

        // Se realiza la entrega de las entradas
        comprasService.entregarEntradas(compra.getId(), VALID_CREDIT_CARD);

        // Se comprueba que las entradas de la compra ya han sido entregadas
        Compra updatedCompra = compraDao.findById(compra.getId()).get();
        assertTrue(updatedCompra.isEntregadas());
    }

    @Test
    public void testEntradasEntregadasMovieAlreadyStarted() {

        User user = crearCuentaUsuario("usuario");
        Pelicula pelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala sala = addSala("Sala 1", 20);

        // Se añade una sesión ya comenzada
        Sesion sesion = addSesion(pelicula, sala, LocalDateTime.now().minusHours(1), BigDecimal.valueOf(5.0), 18);



        Compra compra = addCompra(user, sesion, LocalDateTime.now().minusHours(2));

        // Se comprueba que se lanza la excepción correspondiente
        assertThrows(MovieAlreadyStartedException.class, () ->
                comprasService.entregarEntradas(compra.getId(), compra.getTarjetaBancaria()));
    }

    @Test
    public void testEntregarEntradasTicketsAlreadyDelivered()
            throws InstanceNotFoundException, SinLocalidadesDisponiblesException,
            MovieAlreadyStartedException, TicketsAlreadyDeliveredException, CreditCardDoesNotMatchException {
        User user = crearCuentaUsuario("usuario");
        Pelicula pelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala sala = addSala("Sala 1", 20);
        Sesion sesion = addSesion(pelicula, sala, validSesionDate, BigDecimal.valueOf(5.0), 18);

        Compra compra = comprasService.comprar(user.getId(), sesion.getId(), 5, VALID_CREDIT_CARD);

        // Se realiza la entrega de las entradas
        comprasService.entregarEntradas(compra.getId(), VALID_CREDIT_CARD);

        // Se comprueba que se lanza la excepción correspondiente
        assertThrows(TicketsAlreadyDeliveredException.class, () ->
                comprasService.entregarEntradas(compra.getId(), VALID_CREDIT_CARD));

    }

    @Test
    public void testEntregarEntradasCreditCardDoesNotMatch()
            throws InstanceNotFoundException, SinLocalidadesDisponiblesException, MovieAlreadyStartedException {

        User user = crearCuentaUsuario("usuario");
        Pelicula pelicula = addPelicula("As Bestas", "As bestas es una película dirigida por Rodrigo Sorogoyen", 137);
        Sala sala = addSala("Sala 1", 20);
        Sesion sesion = addSesion(pelicula, sala, validSesionDate, BigDecimal.valueOf(5.0), 18);

        Compra compra = comprasService.comprar(user.getId(), sesion.getId(), 5, VALID_CREDIT_CARD);

        // Se comprueba que se lanza la excepción correspondiente
        assertThrows(CreditCardDoesNotMatchException.class, () ->
                comprasService.entregarEntradas(compra.getId(), INVALID_CREDIT_CARD));
    }

    @Test
    public void testEntregarEntradasNonExistentPurchase() {

        // Se comprueba que se lanza la excepción correspondiente
        assertThrows(InstanceNotFoundException.class, () ->
                comprasService.entregarEntradas(-1L, VALID_CREDIT_CARD));
    }
}
