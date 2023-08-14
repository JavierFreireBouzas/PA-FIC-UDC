package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EntregaParamsDto {

    private String tarjetaBancaria;

    @NotNull
    @Size(min=16, max = 16)
    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(String tarjetaBancaria) {
        this.tarjetaBancaria = tarjetaBancaria;
    }
}
