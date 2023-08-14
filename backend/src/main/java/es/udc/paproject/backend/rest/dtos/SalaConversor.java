package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Sala;

public class SalaConversor {

    private SalaConversor(){};

    public static SalaDto toSalaDto(Sala sala){
        return new SalaDto(sala.getId(), sala.getNombreSala(), sala.getCapacidad());
    }

}
