package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ComprasServiceImpl implements ComprasService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private CompraDao compraDao;

    @Autowired
    private SesionDao sesionDao;

    @Override
    public Compra comprar(Long userId, Long sesionId, int numLocalidades, String tarjetaBancaria)
            throws InstanceNotFoundException, SinLocalidadesDisponiblesException, MovieAlreadyStartedException {

        User currentUser = permissionChecker.checkUser(userId);

        Optional<Sesion> sesion = sesionDao.findById(sesionId);

        if(!sesion.isPresent())
            throw new InstanceNotFoundException("project.entities.sesion", sesionId);

        Sesion currentSesion = sesion.get();

        if (currentSesion.getFecha().isBefore(LocalDateTime.now())) {
            throw new MovieAlreadyStartedException(currentSesion.getId(), currentSesion.getPelicula().getId());
        }

        int localidadesLibres = currentSesion.getLocalidadesLibres();

        //Comprobamos que tengamos localidades suficientes
        if(localidadesLibres < numLocalidades) {
            throw new SinLocalidadesDisponiblesException(currentSesion.getSala().getNombreSala(),
                    currentSesion.getPelicula().getTitulo(), currentSesion.getFecha().toString());
        }

        //Realizamos la compra
        Compra currentCompra = new Compra(currentUser, currentSesion, LocalDateTime.now(), numLocalidades, tarjetaBancaria, false);
        compraDao.save(currentCompra);

        //Modificamos el número de localidades libres
        currentSesion.setLocalidadesLibres(localidadesLibres-numLocalidades);
        sesionDao.save(currentSesion);

        return currentCompra;
    }

    @Override
    @Transactional(readOnly=true)
    public Block<Compra> encontrarCompras(Long userId, int page, int size) {

        Slice<Compra> slice = compraDao.findByUserIdOrderByFechaDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    @Override
    public Long entregarEntradas(Long compraId, String tarjetaBancaria)
            throws TicketsAlreadyDeliveredException, MovieAlreadyStartedException,
            CreditCardDoesNotMatchException, InstanceNotFoundException {

        // Comprobar que la compra existe
        Optional<Compra> compra = compraDao.findById(compraId);
        if (!compra.isPresent()) {
            throw new InstanceNotFoundException("project.entities.compra", compraId);
        }

        // Comprobar que las entradas aún no están entregadas
        if (compra.get().isEntregadas()) {
            throw new TicketsAlreadyDeliveredException(compraId);
        }

        // Comprobar que la película aún no empezó
        Sesion sesion = compra.get().getSesion();
        Pelicula pelicula = sesion.getPelicula();
        LocalDateTime fechaComienzo = compra.get().getSesion().getFecha();
        if (fechaComienzo.isBefore(LocalDateTime.now())) {
            throw new MovieAlreadyStartedException(sesion.getId(), pelicula.getId());
        }

        // Validar la tarjeta introducida con la tarjeta asociada a la compra
        if (!tarjetaBancaria.equals(compra.get().getTarjetaBancaria())) {
            throw new CreditCardDoesNotMatchException(compraId, tarjetaBancaria);
        }

        // Se actualiza la compra como hecha y se guarda en la BBDD
        Compra updatedCompra = compra.get();
        updatedCompra.setEntregadas(true);
        compraDao.save(updatedCompra);

        return updatedCompra.getId();
    }

}
