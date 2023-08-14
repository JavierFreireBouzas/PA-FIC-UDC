package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompraParamsDto {

    private Long sesionId;
    private int numLocalidades;

    private String tarjetaBancaria;


    @NotNull
    public Long getSesionId() {
        return sesionId;
    }

    public void setSesionId(Long sesionId) {
        this.sesionId = sesionId;
    }

    @NotNull
    @Min(value = 1, message = "Compre al menos una entrada.")
    @Max(value = 10, message = "No puede comprar más de 10 entradas.")
    public int getNumLocalidades() {
        return numLocalidades;
    }

    public void setNumLocalidades(int numLocalidades) {
        this.numLocalidades = numLocalidades;
    }

    @NotNull
    @Size(min=16, max = 16)
    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(String tarjetaBancaria) {
        this.tarjetaBancaria = tarjetaBancaria;
    }
}
