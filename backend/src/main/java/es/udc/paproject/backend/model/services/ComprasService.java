package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.exceptions.*;

public interface ComprasService {

    Compra comprar(Long userId, Long sesionId, int numLocalidades, String tarjetaBancaria)
        throws InstanceNotFoundException, SinLocalidadesDisponiblesException, MovieAlreadyStartedException;

    Block<Compra> encontrarCompras(Long userId, int page, int size);

    Long entregarEntradas(Long compraId, String tarjetaBancaria)
            throws TicketsAlreadyDeliveredException, MovieAlreadyStartedException,
            CreditCardDoesNotMatchException, InstanceNotFoundException;

}
